package com.crostage.trainchecker.data.network

import android.util.Log
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "https://pass.rzd.ru/"

object RetrofitBuilder {

    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit {

        if (retrofit == null) {

            val client = OkHttpClient.Builder()
                .cookieJar(UvCookieJar())

            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }


    fun getApi(): ApiRequests {
        return retrofit!!.create(ApiRequests::class.java)
    }

}


private class UvCookieJar : CookieJar {

    private val cookies = mutableListOf<Cookie>()

    override fun saveFromResponse(url: HttpUrl, cookieList: List<Cookie>) {
        if (cookies.isEmpty())
            cookies.addAll(cookieList)
// или так?
//        cookies.clear()
//        cookies.addAll(cookieList)
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        val TAG = "RetrofitBuilder"

        Log.d(TAG, cookies.toString())
        return cookies
    }

}