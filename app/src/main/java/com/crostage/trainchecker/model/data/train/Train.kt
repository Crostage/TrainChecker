package com.crostage.trainchecker.model.data.train

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Entity(tableName = "trains")
data class Train(
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
    @PrimaryKey
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
//{
//    @PrimaryKey(autoGenerate = false)
//    var sid: String = trainNumber+dateStart+timeStart
//}

