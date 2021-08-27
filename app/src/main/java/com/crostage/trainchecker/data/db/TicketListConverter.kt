package com.crostage.trainchecker.data.db

import androidx.room.TypeConverter
import com.crostage.trainchecker.model.data.train.TicketDto
import com.google.gson.Gson
import java.util.*

/**
 * Конвертер списка поездов для сохранения в БД, Room
 *
 */


class TicketListConverter {

    @TypeConverter
    fun listToJson(value: List<TicketDto>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<TicketDto>::class.java).toList()
}