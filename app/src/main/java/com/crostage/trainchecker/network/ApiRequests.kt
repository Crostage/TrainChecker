package com.crostage.trainchecker.network

import com.crostage.trainchecker.model.stationRequest.StationResult
import com.crostage.trainchecker.model.trainRequest.SearchResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequests {

    //https://pass.rzd.ru/timetable/public/ru?layer_id=5827&dir=0&tfl=3&code0=2000002&dt0=30.07.2021&code1=2001320

    //https://pass.rzd.ru/timetable/public/ru?&layer_id=5827&dir=0&tfl=3&code0=0&code1=2001320&dt0=30.07.2021

    @GET("timetable/public/ru?")
    fun getTrains(
        @Query("layer_id") id: Int = 5827,
        @Query("dir") dir: Int = 0,
        @Query("tfl") tfl: Int = 3,
        @Query("code0") codeFrom: Int,
        @Query("code1") codeTo: Int,
        @Query("dt0") date: String

    ): Call<SearchResult>


    @GET("suggester?")
    fun getStations(
        @Query("lang") lang: String = "ru",
        @Query("stationNamePart") stationName: String
    ): Call<StationResult>


}