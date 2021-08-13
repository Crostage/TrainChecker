package com.crostage.trainchecker.model.data.station

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Station(
    @SerializedName("c")
    @Expose
    val stationCode: Int,    //код станции
    @SerializedName("n")
    @Expose
    val stationName: String //название стацнии
):Serializable