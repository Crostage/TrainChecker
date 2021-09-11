package com.crostage.trainchecker.data.model.train

import com.crostage.trainchecker.domain.model.Ticket
import com.google.gson.annotations.SerializedName


data class TrainEntity(
    val carrier: String, //тип поезда ФПК
    val brand: String, //название поезда
    @SerializedName("cars")
    val ticketList: List<Ticket>, //массив билетов
    @SerializedName("code0")
    val codeStationFrom: Int, //код станции отправления
    @SerializedName("code1")
    val codeStationTo: Int, //код станции прибытия
    @SerializedName("route0")
    val nameStationFrom: String, //название станции отправления
    @SerializedName("route1")
    val nameStationTo: String, //название станции прибытия
    @SerializedName("date1")
    val dateEnd: String, //дата прибытия
    @SerializedName("date0")
    val dateStart: String, //дата оптравления
    @SerializedName("number")
    val trainNumber: String, //номер поезда
    @SerializedName("time0")
    val timeStart: String,  //время отправления
    @SerializedName("time1")
    val timeEnd: String,  //время прибытия
    val timeInWay: String, //время в пути
) 

