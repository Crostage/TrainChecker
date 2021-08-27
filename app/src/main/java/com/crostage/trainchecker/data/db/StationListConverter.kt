package com.crostage.trainchecker.data.db

import androidx.room.TypeConverter
import com.crostage.trainchecker.model.data.station.StationEntity
import com.google.gson.Gson
import java.util.*

/**
 * Конвертер списка станций для сохранения в БД, Room
 *
 */

class StationListConverter {

    @TypeConverter
    fun listToJson(value: List<StationEntity>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) =
        Gson().fromJson(value, Array<StationEntity>::class.java).toList()
}