package com.crostage.trainchecker.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Модель мест в вагоне для domain слоя
 *
 * @property free кол-во свободных мест
 * @property tariff цена билета
 * @property label тип места
 */
@Parcelize
data class Seat(
    val free: Int,
    val tariff: Int,
    val label: String,
) : Parcelable