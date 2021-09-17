package com.crostage.trainchecker.data.model.train

import com.crostage.trainchecker.domain.model.Ticket
import com.google.gson.annotations.SerializedName


/**
 * Модель поезда полученная от сервера
 *
 * @property carrier тип поезда
 * @property brand название поезда
 * @property ticketList список билетов [Ticket]
 * @property codeStationFrom код станции отправлени
 * @property codeStationTo код станции прибытия
 * @property nameStationFrom название станции отправления
 * @property nameStationTo название станции прибытия
 * @property dateEnd дата прибытия
 * @property dateStart дата оптравления
 * @property trainNumber номер поезда
 * @property timeStart время отправления
 * @property timeEnd время прибытия
 * @property timeInWay время в пути
 */
data class TrainEntity(
    val carrier: String,
    val brand: String,
    @SerializedName("cars")
    val ticketList: List<Ticket>,
    @SerializedName("code0")
    val codeStationFrom: Int,
    @SerializedName("code1")
    val codeStationTo: Int,
    @SerializedName("route0")
    val nameStationFrom: String,
    @SerializedName("route1")
    val nameStationTo: String,
    @SerializedName("date1")
    val dateEnd: String,
    @SerializedName("date0")
    val dateStart: String,
    @SerializedName("number")
    val trainNumber: String,
    @SerializedName("time0")
    val timeStart: String,
    @SerializedName("time1")
    val timeEnd: String,
    val timeInWay: String,
) 

