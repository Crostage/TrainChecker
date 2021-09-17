package com.crostage.trainchecker.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Модель вагона поезда для domain слоя
 *
 * @property carNumber номер вагона
 * @property carType тип вагона
 * @property clsType класс вагона
 * @property tariff цена билета в вагоне
 * @property seats список мест
 */
@Parcelize
data class Car(
    val carNumber: String,
    val carType: String,
    val clsType: String,
    val tariff: String,
    val seats: List<Seat>,
) : Parcelable