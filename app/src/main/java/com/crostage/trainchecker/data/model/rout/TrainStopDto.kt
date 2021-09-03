package com.crostage.trainchecker.data.model.rout

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TrainStopDto(
    @SerializedName("Code")
    @Expose
    val code: Int,
    @SerializedName("DepTime")
    @Expose
    val arriveTime: String?,
    @SerializedName("Distance")
    @Expose
    val distance: Int,
    @SerializedName("Station")
    @Expose
    val stationName: String,
)