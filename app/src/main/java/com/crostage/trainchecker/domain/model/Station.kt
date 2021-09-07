package com.crostage.trainchecker.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Station(
    val stationCode: Int,    //код станции
    val stationName: String, //название стацнии
) : Parcelable