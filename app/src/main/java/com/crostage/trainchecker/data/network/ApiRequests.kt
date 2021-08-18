package com.crostage.trainchecker.data.network

import com.crostage.trainchecker.model.data.BaseRequest
import com.crostage.trainchecker.model.data.BaseRoutesRequest
import com.crostage.trainchecker.model.data.rout.RoutesResult
import com.crostage.trainchecker.model.data.station.Station
import com.crostage.trainchecker.model.data.car.TicketRequestResult
import com.crostage.trainchecker.model.data.train.SearchResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequests {

    //https://pass.rzd.ru/timetable/public/ru?layer_id=5827&dir=0&tfl=3&code0=2000002&code1=2001320
    // &dt0=27.07.2021

    @GET("timetable/public/ru?")
    fun getTrains(
        @Query("layer_id") layerId: Int = 5827,
        @Query("dir") dir: Int = 0,
        @Query("tfl") tfl: Int = 1, //только поезда
        @Query("code0") codeFrom: Int,
        @Query("code1") codeTo: Int,
        @Query("dt0") date: String,

        ): Call<BaseRequest>

    //https://pass.rzd.ru/timetable/public/ru?layer_id=5804&rid=16637067931&json=y

    @GET("timetable/public/ru?")
    fun getResultFromSearchRid(
        @Query("layer_id") layerId: Int,
        @Query("rid") requestId: Long,
        @Query("json") json: String = "y",
    ): Call<SearchResult>

    @GET("timetable/public/ru?")
    fun getResultFromRoutesRid(
        @Query("layer_id") layerId: Int,
        @Query("rid") requestId: Long,
        @Query("json") json: String = "y",
    ): Call<RoutesResult>

    @GET("timetable/public/ru?")
    fun getResultFromTickersRid(
        @Query("layer_id") layerId: Int,
        @Query("rid") requestId: Long,
        @Query("json") json: String = "y",
    ): Call<TicketRequestResult>

    @GET("suggester?")
    fun getStation(
        @Query("lang") lang: String = "ru",
        @Query("stationNamePart") stationName: String,
    ): Call<List<Station>>

    //http://pass.rzd.ru/timetable/public/ru?layer_id=5804&train_num=072E&date=13.03.2020
    // &json=y&format=array

    @GET("timetable/public/ru?")
    fun getRouters(
        @Query("layer_id") layerId: Int = 5804,
        @Query("train_num") number: String,
        @Query("date") date: String = "13.03.2020",
        @Query("json") json: String = "y",
        @Query("format") format: String = "array",
    ): Call<BaseRoutesRequest>


    //https://pass.rzd.ru/timetable/public/ru?layer_id=5764&dir=0&tfl=1&code0=2000000
    // &code1=2064130&dt0=17.08.2021&time0=10:46&tnum0=479%D0%90

    @GET("timetable/public/ru?")
    fun getSeats(
        @Query("layer_id") layerId: Int = 5764,
        @Query("dir") dir: Int = 0,
        @Query("tfl") tfl: Int = 1, //только поезда
        @Query("code0") codeFrom: Int,
        @Query("code1") codeTo: Int,
        @Query("dt0") date: String,
        @Query("time0") time: String,
        @Query("tnum0") number: String,

        ): Call<BaseRequest>

}