package com.crostage.trainchecker.data.db

import androidx.room.TypeConverter
import com.crostage.trainchecker.data.model.train.Ticket
import com.google.gson.Gson
import java.util.*

/**
 * Конвертер списка поездов для сохранения в БД, Room
 *
 */


class TicketListConverter {

    @TypeConverter
    fun listToJson(value: List<Ticket>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Ticket>::class.java).toList()
}