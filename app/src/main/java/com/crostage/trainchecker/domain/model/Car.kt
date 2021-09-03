package com.crostage.trainchecker.domain.model

data class Car(
    val carNumber: String, //номер вагона
    val carType: String, //тип вагона
    val clsType: String, // 3Э
    val tariff: String, //тариф руб
    val tickets: List<Seat>, //список мест

)