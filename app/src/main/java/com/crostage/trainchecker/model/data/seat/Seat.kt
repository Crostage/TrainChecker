package com.crostage.trainchecker.model.data.seat

data class Seat(
    val free: Int, //свободные места
    val tariff: Int, //тариф в рублях
    val label: String, //тип места полный
)