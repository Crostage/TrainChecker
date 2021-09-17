package com.crostage.trainchecker.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Модель поезда для domain слоя
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
 * @property isFavourite находится в отслеживаемых
 */
@Parcelize
data class Train(
    val carrier: String,
    val brand: String,
    val ticketList: List<Ticket>,
    val codeStationFrom: Int,
    val codeStationTo: Int,
    val nameStationFrom: String,
    val nameStationTo: String,
    val dateEnd: String,
    val dateStart: String,
    val trainNumber: String,
    val timeStart: String,
    val timeEnd: String,
    val timeInWay: String,
    var isFavourite: Boolean,
) : Parcelable {

    //пришлось переопределять ручками, тк при получении списка поездов из сети некоторые поля
    //динамичны. Например меняется массив билетов.

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

