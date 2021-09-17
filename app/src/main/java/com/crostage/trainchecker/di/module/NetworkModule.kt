package com.crostage.trainchecker.di.module

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.crostage.trainchecker.data.network.ApiRequests
import com.crostage.trainchecker.data.network.RetrofitBuilder
import com.crostage.trainchecker.utils.Constant
import dagger.Module
import dagger.Provides
import okhttp3.CacheControl
import okhttp3.Interceptor
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Provider
import javax.inject.Singleton

/**
 * Модуль для внедрения зависимостей для работы с сетью
 *
 */
@Module
class NetworkModule {

    /**
     * Предоставляет [File] файл, содержаший путь к кэшу
     *
     */
    @Provides
    fun provideCacheDir(context: Context) = File(context.cacheDir, Constant.CACHE_CHILD)

    /**
     * Предоставляет [Int] тип соединения 0 - нет сети, 1 - моибльный данные, 2 - wi-fi
     *
     */
    @Provides
    fun provideConnectionType(context: Context): Int {
        var connectionType = 0
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        cm?.let {
            val capabilities =
                cm.getNetworkCapabilities(cm.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        connectionType = 2
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        connectionType = 1
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> {
                        connectionType = 3
                    }
                }
            }

        }
        return connectionType
    }

    /**
     * Предоставляет [Interceptor] перехватчик для оффлайн кэишрования
     *
     */
    @Provides
    fun provideOfflineInterceptor(connectionType: Provider<Int>) = Interceptor { chain ->
        val ct = connectionType.get()
        Log.d("daggerTest", "OfflineInterceptor called $ct")

        var request = chain.request()
        if (ct == 0) {
            val cacheControl = CacheControl.Builder()
                .maxStale(30, TimeUnit.MINUTES)
                .build()

            request = request.newBuilder()
                .removeHeader(Constant.HEADER_PRAGMA)
                .removeHeader(Constant.HEADER_CACHE_CONTROL)
                .header(Constant.HEADER_CACHE_CONTROL, cacheControl.toString())
                .build()
        }
        chain.proceed(request)
    }

    /**
     * Предоставляет [ApiRequests] класс для работы с сетевыми запросам
     *
     */
    @Provides
    @Singleton
    fun provideRetrofit(cacheDir: File, offlineInterceptor: Interceptor): ApiRequests =
        RetrofitBuilder(offlineInterceptor).getApi(cacheDir)
}