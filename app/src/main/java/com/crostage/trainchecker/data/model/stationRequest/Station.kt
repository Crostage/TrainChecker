package com.crostage.trainchecker.data.model.stationRequest

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stations")
data class Station(
    @PrimaryKey
    val c: Int,    //код станции
    val n: String //название стацнии
)