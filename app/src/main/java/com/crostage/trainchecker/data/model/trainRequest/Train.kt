package com.crostage.trainchecker.data.model.trainRequest

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "trains")
data class Train(
    val subtrainCatName: String, //тип поезда
    val carrier: String, //тип поезда ФПК
    val brand: String, //название поезда
//    val cars: List<Any>, //массив билетов = null
    val code0: Int, //код станции отправления
    val code1: Int, //код станции прибытия
    val route0: String, //название станции отправления
    val route1: String, //название станции прибытия
    val date1: String, //дата прибытия
    val date0: String, //дата оптравления
    val number: String, //номер поезда
    val time0: String,  //время отправления
    val time1: String,  //время прибытия
    val timeInWay: String, //время в пути
    val mvMode: String //когда ходит
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

