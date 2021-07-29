package com.crostage.trainchecker.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://pass.rzd.ru/"

object RetrofitBuilder {

    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()        }
        return retrofit!!
    }


    fun getApi(): ApiRequests {
        return retrofit!!.create(ApiRequests::class.java)
    }

}