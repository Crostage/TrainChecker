package com.crostage.trainchecker.data.repository

import androidx.room.TypeConverter
import com.crostage.trainchecker.data.model.station.Station
import com.google.gson.Gson
import java.util.*

/**
 * Конвертер списка станций для сохранения в БД, Room
 *
 */

class StationListConverter {

    @TypeConverter
    fun listToJson(value: List<Station>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Station>::class.java).toList()
}