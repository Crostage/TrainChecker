package com.crostage.trainchecker.data.network

import android.util.Log
import com.crostage.trainchecker.data.model.rout.Response
import com.crostage.trainchecker.data.network.adapter.RouteResponseDeserializer
import com.crostage.trainchecker.utils.Constant.Companion.BASE_URL
import com.crostage.trainchecker.utils.Constant.Companion.CACHE_SIZE
import com.crostage.trainchecker.utils.Constant.Companion.HEADER_CACHE_CONTROL
import com.crostage.trainchecker.utils.Constant.Companion.HEADER_PRAGMA
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.net.CookieManager
import java.net.CookiePolicy
import java.util.concurrent.TimeUnit

/**
 * Билдер для получения для работы с сетью
 *
 * @property offlineInterceptor перехватчик для оффлайне кэширования
 */
class RetrofitBuilder(private val offlineInterceptor: Interceptor) {

    companion object {
        private const val TAG = "RetrofitBuilder"
    }

    /**
     * Получаем объект для отрпавления сетевых запросов
     *
     * @param dir место хранения кэша
     * @return [ApiRequests] для отправки запросов
     */
    fun getApi(dir: File): ApiRequests =
        getRetrofit(dir).create(ApiRequests::class.java)

    private fun getRetrofit(dir: File): Retrofit {
        val cookieManager = CookieManager()
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL)

        val cacheSize = CACHE_SIZE
        val cache = Cache(dir, cacheSize.toLong())
        val client = OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(httpLoggingInterceptor)
            .addNetworkInterceptor(onlineInterceptor)
            .addInterceptor(offlineInterceptor)
            .cookieJar(JavaNetCookieJar(cookieManager))

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client.build())
            .addConverterFactory(GsonConverterFactory
                .create(createResponseTypeAdapter()))
            .build()
    }


    private fun createResponseTypeAdapter(): Gson =
        GsonBuilder().registerTypeAdapter(Response::class.java, RouteResponseDeserializer())
            .create()


    private var onlineInterceptor: Interceptor = Interceptor { chain ->
        Log.d(TAG, "ONlineInterceptor called")
        val response = chain.proceed(chain.request())

        val cacheControl = CacheControl.Builder()
            .maxAge(2, TimeUnit.MINUTES)
            .build()

        response.newBuilder()
            .removeHeader(HEADER_PRAGMA)
            .removeHeader(HEADER_CACHE_CONTROL)
            .header(HEADER_CACHE_CONTROL, cacheControl.toString())
            .build()
    }


    private var httpLoggingInterceptor = HttpLoggingInterceptor { Log.d(TAG, "http:log $it") }
        .setLevel(HttpLoggingInterceptor.Level.BODY)

}
