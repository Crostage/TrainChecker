package com.crostage.trainchecker.data.model.stationRequest

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "stations")
data class Station(
    @PrimaryKey
    @SerializedName("c")
    @Expose
    val stationCode: Int,    //код станции
    @SerializedName("n")
    @Expose
    val stationName: String //название стацнии
)