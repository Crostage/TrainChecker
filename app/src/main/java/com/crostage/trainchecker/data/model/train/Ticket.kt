package com.crostage.trainchecker.data.model.train

import java.io.Serializable

data class Ticket(
    val freeSeats: Int, //свободные места
    val tariff: Int, //тариф в рублях
    val type: String, //тип места сокращенный
    val typeLoc: String, //тип места полный
) : Serializable