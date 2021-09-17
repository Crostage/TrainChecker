package com.crostage.trainchecker.data.model.seat

import com.crostage.trainchecker.domain.model.Seat
import com.google.gson.annotations.SerializedName

/**
 * Модель вагона из сети
 *
 * @property carNumber номер вагона
 * @property carType тип вагона
 * @property clsType класс вагона
 * @property tariff цена биллетов в вагоне
 * @property seats список мест [Seat]
 */
data class CarDto(
    @SerializedName("cnumber")
    val carNumber: String,
    @SerializedName("typeLoc")
    val carType: String,
    val clsType: String,
    val tariff: String,
    val seats: List<Seat>,

    )