package com.crostage.trainchecker.domain.model

data class Seat(
    val free: Int, //свободные места
    val tariff: Int, //тариф в рублях
    val label: String, //тип места полный
)