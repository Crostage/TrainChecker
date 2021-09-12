package com.crostage.trainchecker.data.model.seat

import com.crostage.trainchecker.domain.model.Seat
import com.google.gson.annotations.SerializedName

data class CarDto(
    @SerializedName("cnumber")
    val carNumber: String, //номер вагона
    @SerializedName("typeLoc")
    val carType: String, //тип вагона
    val clsType: String, // 3Э
    val tariff: String, //тариф руб
    @SerializedName("seats")
    val tickets: List<Seat>, //список мест

)