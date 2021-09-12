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

@Module
class NetworkModule {

    @Provides
    fun provideCacheDir(context: Context) = File(context.cacheDir, Constant.CACHE_CHILD)

    @Provides
    fun provideConnectionType(context: Context): Int {
        var connectionType = 0 // Returns connection type. 0: none; 1: mobile data; 2: wifi
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

    @Provides
    @Singleton
    fun provideRetrofit(cacheDir: File, offlineInterceptor: Interceptor): ApiRequests =
        RetrofitBuilder(offlineInterceptor).getApi(cacheDir)
}