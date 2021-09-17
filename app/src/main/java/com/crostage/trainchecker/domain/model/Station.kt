package com.crostage.trainchecker.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Модель стацнии для domain слоя
 *
 * @property stationCode код станции
 * @property stationName название стацнии
 */
@Parcelize
data class Station(
    val stationCode: Int,
    val stationName: String,
) : Parcelable