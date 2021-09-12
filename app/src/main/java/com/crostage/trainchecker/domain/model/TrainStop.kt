package com.crostage.trainchecker.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TrainStop(
    val code: Int,
    val arriveTime: String,
    val distance: Int,
    val stationName: String,
) : Parcelable