package com.crostage.trainchecker.data.model.station

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.crostage.trainchecker.utils.Constant.Companion.TABLE_NAME_LAST_PICK
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = TABLE_NAME_LAST_PICK)
data class Station(
    @PrimaryKey
    @SerializedName("c")
    @Expose
    val stationCode: Int,    //код станции
    @SerializedName("n")
    @Expose
    val stationName: String, //название стацнии
):Serializable