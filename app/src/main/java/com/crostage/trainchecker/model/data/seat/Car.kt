package com.crostage.trainchecker.model.data.seat

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Car(
    @SerializedName("cnumber")
    @Expose
    val carNumber: String, //номер вагона
    @SerializedName("typeLoc")
    @Expose
    val carType: String, //тип вагона
    val clsType: String, // 3Э
    val tariff: String, //тариф руб
    @SerializedName("seats")
    @Expose
    val tickets: List<Seat>, //список мест

)