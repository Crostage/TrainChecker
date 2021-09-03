package com.crostage.trainchecker.domain.model

data class TrainStop(
    val code: Int,
    val arriveTime: String,
    val distance: Int,
    val stationName: String,
)