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

    /**
     * Метод для прямого преобразование списка [Ticket] в JSON
     *
     * @param value список [Ticket] для преобразования
     * @return строкове представление JSON
     */
    @TypeConverter
    fun listToJson(value: List<Ticket>?): String = Gson().toJson(value)

    /**
     * Метод для обратного преобразование JSON в список
     *
     * @param value строка для преобразваония
     * @return список [Ticket]
     */
    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Ticket>::class.java).toList()
}