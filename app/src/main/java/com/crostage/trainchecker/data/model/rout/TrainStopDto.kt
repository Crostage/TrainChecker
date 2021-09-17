package com.crostage.trainchecker.data.model.rout

import com.google.gson.annotations.SerializedName


/**
 * Модель остановки из сети
 *
 * @property code код остановки
 * @property arriveTime время прибытия на остановку
 * @property distance дистанция от начала до остановки
 * @property stationName название станции остановки
 */
data class TrainStopDto(
    @SerializedName("Code")
    val code: Int,
    @SerializedName("DepTime")
    val arriveTime: String?,
    @SerializedName("Distance")
    val distance: Int,
    @SerializedName("Station")
    val stationName: String,
)