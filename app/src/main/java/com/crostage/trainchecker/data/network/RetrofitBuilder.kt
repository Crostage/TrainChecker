package com.crostage.trainchecker.data.network

import android.content.Context
import com.crostage.trainchecker.utils.Constant.Companion.BASE_URL
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.lang.ref.WeakReference

/**
 * Класс для работы с сетью
 */


object RetrofitBuilder {

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

            //todo запросы кэшируются, но все равно обращаются в сеть

            val cacheSize = 10 * 1024 * 1024 // 10 MiB
            val cacheDir = File(mContext.get()?.cacheDir, "HttpCache")
            val cache = Cache(cacheDir, cacheSize.toLong())
            val client = OkHttpClient.Builder().cache(cache).cookieJar(UvCookieJar())

            retrofit = Retrofit.Builder().baseUrl(BASE_URL).client(client.build())
                .addConverterFactory(GsonConverterFactory.create()).build()
        }
        return retrofit!!
    }


    val Retrofit.getApi: ApiRequests
        get() = this.create(ApiRequests::class.java)


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