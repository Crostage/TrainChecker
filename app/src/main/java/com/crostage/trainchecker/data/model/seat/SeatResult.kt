package com.crostage.trainchecker.data.model.seat

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SeatResult(
    @SerializedName("lst")
    @Expose
    val response: List<CarResponse>,
)

data class CarResponse(
    val cars: List<CarDto>,
)