package com.crostage.trainchecker.data.model.station

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.crostage.trainchecker.utils.Constant.Companion.TABLE_NAME_LAST_PICK
import com.google.gson.annotations.SerializedName

@Entity(tableName = TABLE_NAME_LAST_PICK)
data class StationEntity(
    @PrimaryKey
    @SerializedName("c")
    val stationCode: Int,    //код станции
    @SerializedName("n")
    val stationName: String, //название стацнии
)