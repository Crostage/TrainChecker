package com.crostage.trainchecker.data.repository

import androidx.room.TypeConverter
import com.crostage.trainchecker.model.data.station.Station
import com.crostage.trainchecker.model.data.train.Ticket
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*


class TicketListConverter {

    @TypeConverter
    fun listToJson(value: List<Ticket>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Ticket>::class.java).toList()
}