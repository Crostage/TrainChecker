package com.crostage.trainchecker.data.db.converter

import androidx.room.TypeConverter
import com.crostage.trainchecker.data.model.station.StationEntity
import com.google.gson.Gson
import java.util.*

/**
 * Конвертер списка станций для сохранения в БД, Room
 *
 */

class StationListConverter {

    /**
     * Метод для прямого преобразование списка в JSON
     *
     * @param value список [StationEntity] для преобразования
     * @return строкове представление JSON
     */
    @TypeConverter
    fun listToJson(value: List<StationEntity>?): String = Gson().toJson(value)

    /**
     * Метод для обратного преобразование JSON в список
     *
     * @param value строка для преобразваония
     * @return список [StationEntity]
     */
    @TypeConverter
    fun jsonToList(value: String) =
        Gson().fromJson(value, Array<StationEntity>::class.java).toList()
}