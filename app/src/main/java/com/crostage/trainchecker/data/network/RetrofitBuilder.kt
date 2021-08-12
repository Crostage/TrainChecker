package com.crostage.trainchecker.data.network

import android.content.Context
import com.crostage.trainchecker.utils.Constant.Companion.BASE_URL
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.lang.ref.WeakReference


object RetrofitBuilder {

    private var retrofit: Retrofit? = null


    fun getClient(context: Context): Retrofit {

        val mContext = WeakReference(context)

        if (retrofit == null) {

            val cacheSize = 10 * 1024 * 1024 // 10 MiB
            val cacheDir = File(mContext.get()?.cacheDir, "HttpCache")
            val cache = Cache(cacheDir, cacheSize.toLong())
            val client = OkHttpClient.Builder().cache(cache).cookieJar(UvCookieJar())

//            val client = OkHttpClient.Builder().cookieJar(UvCookieJar())

            retrofit = Retrofit.Builder().baseUrl(BASE_URL).client(client.build())
                .addConverterFactory(GsonConverterFactory.create()).build()
        }
        return retrofit!!
    }


    val getApi: ApiRequests by lazy {
        retrofit!!.create(ApiRequests::class.java)
    }


}


/**
 * Класс для сохранения и отправки куки
 *
 */

private class UvCookieJar : CookieJar {

    private val cookies = mutableListOf<Cookie>()

    override fun saveFromResponse(url: HttpUrl, cookieList: List<Cookie>) {
        if (cookies.isEmpty()) cookies.addAll(cookieList)
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> = cookies

}