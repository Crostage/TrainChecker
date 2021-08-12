package com.crostage.trainchecker.data.network

import com.crostage.trainchecker.utils.Constant.Companion.BASE_URL
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitBuilder {

    private var retrofit: Retrofit? = null


    fun getClient(): Retrofit {

        if (retrofit == null) {
            val client = OkHttpClient.Builder().cookieJar(UvCookieJar())

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