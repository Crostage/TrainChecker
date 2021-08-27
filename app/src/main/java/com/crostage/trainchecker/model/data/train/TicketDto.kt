package com.crostage.trainchecker.model.data.train


data class TicketDto(
    val freeSeats: Int, //свободные места
    val tariff: Int, //тариф в рублях
    val type: String, //тип места сокращенный
    val typeLoc: String, //тип места полный
)