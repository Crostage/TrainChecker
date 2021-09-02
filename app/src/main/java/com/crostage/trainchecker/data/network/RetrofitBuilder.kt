package com.crostage.trainchecker.data.network

import android.content.Context
import android.util.Log
import com.crostage.trainchecker.data.network.adapter.ResponseDeserializer
import com.crostage.trainchecker.model.data.rout.Response
import com.crostage.trainchecker.presentation.MainApp
import com.crostage.trainchecker.utils.Constant.Companion.BASE_URL
import com.crostage.trainchecker.utils.Constant.Companion.CACHE_CHILD
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
import java.lang.ref.WeakReference
import java.util.concurrent.TimeUnit


/**
 * Класс для работы с сетью
 */


object RetrofitBuilder {

    private const val TAG = "RetrofitBuilder"
    private var retrofit: Retrofit? = null

    /**
     * Получения клиента для работы с сетью
     *
     * @param context
     * @return [Retrofit]
     */

    fun getClient(context: Context): Retrofit {

        val mContext = WeakReference(context)

        if (retrofit == null) {

            val cacheSize = CACHE_SIZE
            val cacheDir = File(mContext.get()?.cacheDir, CACHE_CHILD)
            val cache = Cache(cacheDir, cacheSize.toLong())
            val client = OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(httpLoggingInterceptor)
                .addNetworkInterceptor(onlineInterceptor)
                .addInterceptor(offlineInterceptor)
                .cookieJar(UvCookieJar())

            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory
                    .create(createResponseTypeAdapter()))
                .build()
        }
        return retrofit!!
    }

    fun createResponseTypeAdapter(): Gson =
        GsonBuilder().registerTypeAdapter(Response::class.java, ResponseDeserializer())
            .create()


    val Retrofit.getApi: ApiRequests
        get() = this.create(ApiRequests::class.java)


    private var onlineInterceptor: Interceptor = Interceptor { chain ->
        Log.d(TAG, "ONlineInterceptor called")
        val request = chain.proceed(chain.request())

        val cacheControl = CacheControl.Builder()
            .maxStale(5, TimeUnit.MINUTES)
            .build()

        request.newBuilder()
            .removeHeader(HEADER_PRAGMA)
            .removeHeader(HEADER_CACHE_CONTROL)
            .header(HEADER_CACHE_CONTROL, cacheControl.toString())
            .build()
    }


    private var offlineInterceptor = Interceptor { chain ->
        Log.d(TAG, "OFFlineInterceptor called")
        var request = chain.request()

        if (!MainApp.hasNetwork()!!) {

            val cacheControl = CacheControl.Builder()
                .maxStale(2, TimeUnit.HOURS)
                .build()

            request = request.newBuilder()
                .removeHeader(HEADER_PRAGMA)
                .removeHeader(HEADER_CACHE_CONTROL)
                .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                .build()
        }
        chain.proceed(request)

    }

    private var httpLoggingInterceptor = HttpLoggingInterceptor { Log.d(TAG, "http:log $it") }
        .setLevel(HttpLoggingInterceptor.Level.BODY)


}


/**
 * Класс для сохранения и отправки куки
 *
 */

private class UvCookieJar : CookieJar {

    private val cookies = mutableListOf<Cookie>()

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        if (this.cookies.isEmpty()) this.cookies.addAll(cookies)
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> = cookies

}

/**
 * Класс перехватчик для работы кэша
 *
 */

