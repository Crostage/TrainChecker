package com.crostage.trainchecker.data.repository

import androidx.room.TypeConverter
import com.crostage.trainchecker.model.data.station.Station
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*


class StationListConverter {

    @TypeConverter
    fun listToJson(value: List<Station>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Station>::class.java).toList()
}