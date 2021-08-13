package com.crostage.trainchecker.model.data.train

import java.io.Serializable

class SearchResult(
    val tp: List<Tp>,
    val result:String
)

 class Tp(
    val list: List<Train>
)

//todo списк билетов
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