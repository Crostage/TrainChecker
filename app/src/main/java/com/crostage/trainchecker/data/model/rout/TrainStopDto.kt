package com.crostage.trainchecker.data.model.rout

import com.google.gson.annotations.SerializedName

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