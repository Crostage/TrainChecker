package com.crostage.trainchecker.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Модель остановок для domain слоя
 *
 * @property code код остановки
 * @property arriveTime время прибытия
 * @property distance расстояние до остановки
 * @property stationName название станции
 */
@Parcelize
data class TrainStop(
    val code: Int,
    val arriveTime: String,
    val distance: Int,
    val stationName: String,
) : Parcelable