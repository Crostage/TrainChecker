package com.crostage.trainchecker.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Модель билета для domain слоя
 *
 * @property freeSeats свободные билеты
 * @property tariff цена билета
 * @property type тип места
 * @property typeLoc тип места сокращенный
 */
@Parcelize
data class Ticket(
    val freeSeats: Int,
    val tariff: Int,
    val type: String,
    val typeLoc: String,
) : Parcelable