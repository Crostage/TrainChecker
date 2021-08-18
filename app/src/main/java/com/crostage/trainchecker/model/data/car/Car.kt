package com.crostage.trainchecker.model.data.car

import Seat
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Car(
    @SerializedName("cnumber")
    @Expose
    val carNumber: String, //номер вагона
    val typeLoc: String, //тип вагона
    val clsType: String, // 3Э
    val tariff: String, //тариф руб
    val seats: List<Seat>, //список мест

)