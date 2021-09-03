package com.crostage.trainchecker.data.model.train

import androidx.room.Entity
import com.crostage.trainchecker.domain.model.Ticket
import com.crostage.trainchecker.utils.Constant.Companion.TABLE_NAME_TRAINS
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


@Entity(tableName = TABLE_NAME_TRAINS, primaryKeys = ["trainNumber", "dateStart", "timeStart"])
data class TrainEntity(
    val carrier: String, //тип поезда ФПК
    val brand: String, //название поезда
    @SerializedName("cars")
    @Expose
    val ticketList: List<Ticket>, //массив билетов
    @SerializedName("code0")
    @Expose
    val codeStationFrom: Int, //код станции отправления
    @SerializedName("code1")
    @Expose
    val codeStationTo: Int, //код станции прибытия
    @SerializedName("route0")
    @Expose
    val nameStationFrom: String, //название станции отправления
    @SerializedName("route1")
    @Expose
    val nameStationTo: String, //название станции прибытия
    @SerializedName("date1")
    @Expose
    val dateEnd: String, //дата прибытия
    @SerializedName("date0")
    @Expose
    val dateStart: String, //дата оптравления
    @SerializedName("number")
    @Expose
    val trainNumber: String, //номер поезда
    @SerializedName("time0")
    @Expose
    val timeStart: String,  //время отправления
    @SerializedName("time1")
    @Expose
    val timeEnd: String,  //время прибытия
    val timeInWay: String, //время в пути
) 

