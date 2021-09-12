package com.crostage.trainchecker.data.network

import com.crostage.trainchecker.data.model.GeneralResult
import com.crostage.trainchecker.data.model.rid.SeatRidResult
import com.crostage.trainchecker.data.model.rid.RouteRidResult
import com.crostage.trainchecker.data.model.station.StationEntity
import com.crostage.trainchecker.utils.Constant.Companion.ROUTE_LAYER_ID
import com.crostage.trainchecker.utils.Constant.Companion.SEAT_LAYER_ID
import com.crostage.trainchecker.utils.Constant.Companion.TRAIN_LAYER_ID
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Отвечает за получение данных онлайн из API РЖД
 *
 */


interface ApiRequests {


    /**
     * Запрос на получение списка станций с кодами по запросу
     *
     * @param lang язык ввода\вывода
     * @param stationName часть имени станции
     * @return список станций [StationEntity]
     */
    @GET("suggester?")
    fun getStation(
        @Query("lang") lang: String = "ru",
        @Query("stationNamePart") stationName: String,
    ): Call<List<StationEntity>>


    /**
     * Запрос на получение маршрута конкретного поезда
     *
     * @param layerId тип запроса
     * @param number номер поезда
     * @param date дата отправления
     * @param json вид данных вывода
     * @param format формат вывода
     * @return возварщает requestID [RouteRidResult]
     */

    @GET("timetable/public/ru?")
    fun getRouters(
        @Query("layer_id") layerId: Int = ROUTE_LAYER_ID,
        @Query("train_num") number: String,
        @Query("date") date: String,
        @Query("json") json: String = "y",
        @Query("format") format: String = "array",
    ): Call<RouteRidResult>


    /**
     * Запрос на получения вагонов поезда с информацией о местах
     *
     * @param layerId тип запроса
     * @param dir с пересадками или без
     * @param tfl тип транспорта: электрички, поезда, и оба типа
     * @param codeFrom код города отправления
     * @param codeTo код города прибытия
     * @param date дата отправления
     * @param time время отправления поезда
     * @param number номер поезда
     * @return возварщает requestID [SeatRidResult]
     */
    @GET("timetable/public/ru?")
    fun getSeats(
        @Query("layer_id") layerId: Int = SEAT_LAYER_ID,
        @Query("dir") dir: Int = 0,
        @Query("tfl") tfl: Int = 1, //только поезда
        @Query("code0") codeFrom: Int,
        @Query("code1") codeTo: Int,
        @Query("dt0") date: String,
        @Query("time0") time: String,
        @Query("tnum0") number: String,
    ): Call<SeatRidResult>

    /**
     * Запрос на получения списка поездов по поисковому запросу, в ответе от сервера будет RID
     *
     * @param layerId тип запроса
     * @param dir с пересадками или без
     * @param tfl тип транспорта: электрички, поезда, и оба типа
     * @param codeFrom код города отправления
     * @param codeTo код города прибытия
     * @param date дата отправления
     * @return возварщает [GeneralResult] для преобразования в список поездов
     */
    @GET("timetable/public/ru?")
    fun getTrains(
        @Query("layer_id") layerId: Int = TRAIN_LAYER_ID,
        @Query("dir") dir: Int = 0,
        @Query("tfl") tfl: Int = 1, //только поезда
        @Query("code0") codeFrom: Int,
        @Query("code1") codeTo: Int,
        @Query("dt0") date: String,
    ): Call<GeneralResult>

    /**
     * Повторный запрос с rid для получения списка поездов
     *
     * @param layerId тип запроса
     * @param requestId id запроса
     * @param json вид данных вывода
     * @param format формат вывода
     * @return модель содержащая список поездов [GeneralResult]
     */

    @GET("timetable/public/ru?")
    fun getResultFromRid(
        @Query("layer_id") layerId: Int,
        @Query("rid") requestId: Long,
        @Query("json") json: String = "y",
        @Query("format") format: String = "array",
    ): Call<GeneralResult>

}