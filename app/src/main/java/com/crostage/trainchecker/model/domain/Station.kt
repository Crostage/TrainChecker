package com.crostage.trainchecker.model.domain

import java.io.Serializable

data class Station(
    val stationCode: Int,    //код станции
    val stationName: String, //название стацнии
):Serializable