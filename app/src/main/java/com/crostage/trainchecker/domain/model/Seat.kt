package com.crostage.trainchecker.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Seat(
    val free: Int, //свободные места
    val tariff: Int, //тариф в рублях
    val label: String, //тип места полный
) : Parcelable