package com.crostage.trainchecker.data.model.seat

data class Seat(
    val free: Int, //свободные места
    val tariff: Int, //тариф в рублях
    val label: String, //тип места полный
)