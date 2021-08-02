package com.crostage.trainchecker.data.network

import com.crostage.trainchecker.data.model.BaseRequest
import com.crostage.trainchecker.data.model.stationRequest.StationResult
import com.crostage.trainchecker.data.model.trainRequest.SearchResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequests {

    //https://pass.rzd.ru/timetable/public/ru?layer_id=5827&dir=0&tfl=3&code0=2000002&dt0=30.07.2021&code1=2001320

    //https://pass.rzd.ru/timetable/public/ru?&layer_id=5827&dir=0&tfl=3&code0=0&code1=2001320&dt0=30.07.2021

    @GET("timetable/public/ru?")
    fun getTrains(
        @Query("layer_id") layerId:Int,
        @Query("dir") dir: Int = 0,
        @Query("tfl") tfl: Int = 1,
        @Query("code0") codeFrom: Int,
        @Query("code1") codeTo: Int,
        @Query("dt0") date: String

    ): Call<BaseRequest>


    //https://pass.rzd.ru/timetable/public/ru?layer_id=5804&rid=16637067931&json=y


    @GET("timetable/public/ru?")
    fun getRequestFromRid(
        @Query("layer_id") layerId: Int,
        @Query("rid") requestId: Long,
        @Query("json") json: String = "y"
    ): Call<SearchResult>


    @GET("suggester?")
    fun getStations(
        @Query("lang") lang: String = "ru",
        @Query("stationNamePart") stationName: String
    ): Call<StationResult>


}