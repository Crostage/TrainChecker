package com.crostage.trainchecker.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Car(
    val carNumber: String, //номер вагона
    val carType: String, //тип вагона
    val clsType: String, // 3Э
    val tariff: String, //тариф руб
    val tickets: List<Seat>, //список мест

) : Parcelable