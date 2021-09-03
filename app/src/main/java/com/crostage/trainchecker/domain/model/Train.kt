package com.crostage.trainchecker.domain.model

import java.io.Serializable


data class Train(
    val carrier: String, //тип поезда ФПК
    val brand: String, //название поезда
    val ticketList: List<Ticket>, //массив билетов
    val codeStationFrom: Int, //код станции отправления
    val codeStationTo: Int, //код станции прибытия
    val nameStationFrom: String, //название станции отправления
    val nameStationTo: String, //название станции прибытия
    val dateEnd: String, //дата прибытия
    val dateStart: String, //дата оптравления
    val trainNumber: String, //номер поезда
    val timeStart: String,  //время отправления
    val timeEnd: String,  //время прибытия
    val timeInWay: String, //время в пути
    var isFavourite: Boolean,
) : Serializable {


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Train

        if (dateStart != other.dateStart) return false
        if (trainNumber != other.trainNumber) return false
        if (timeStart != other.timeStart) return false

        return true
    }

    override fun hashCode(): Int {
        var result = dateStart.hashCode()
        result = 31 * result + trainNumber.hashCode()
        result = 31 * result + timeStart.hashCode()
        return result
    }
}

