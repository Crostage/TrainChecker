package com.crostage.trainchecker.data.model.routRequset

data class TrainStop(
    val ArvTime: String,
    val Code: Int,
    val Days: String,
    val DepTime: String,
    val Distance: Int,
    val Station: String,
    val TailForward: String,
    val WaitingTime: String
)