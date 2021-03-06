package com.crostage.trainchecker.data.model.station

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.crostage.trainchecker.utils.Constant.Companion.TABLE_NAME_LAST_PICK
import com.google.gson.annotations.SerializedName

/**
 * Модель станции для получения из сети и хранения в БД
 *
 * @property stationCode код станции
 * @property stationName имя станции
 */
@Entity(tableName = TABLE_NAME_LAST_PICK)
data class StationEntity(
    @SerializedName("c")
    val stationCode: Int,    //код станции
    @PrimaryKey
    @SerializedName("n")
    val stationName: String, //название стацнии
)