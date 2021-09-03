package com.crostage.trainchecker.data.db.converter

import androidx.room.TypeConverter
import com.crostage.trainchecker.domain.model.Ticket
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