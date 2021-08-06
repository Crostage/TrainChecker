package com.crostage.trainchecker.data.model.trainRequest

import java.io.Serializable

data class Ticket(
    val bFreeInvisible: Boolean,
    val carDataType: Int,
    val disabledPerson: Boolean,
    val freeSeats: Int,
    val itype: Int,
    val pt: Int,
    val servCls: String,
    val tariff: Int,
    val type: String,
    val typeLoc: String
): Serializable