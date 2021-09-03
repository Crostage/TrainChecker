package com.crostage.trainchecker.domain.model

import java.io.Serializable

data class Station(
    val stationCode: Int,    //код станции
    val stationName: String, //название стацнии
):Serializable