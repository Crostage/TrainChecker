package com.crostage.trainchecker.data.db.converter

import com.crostage.trainchecker.ConstForTest.Companion.JSON_STATION_ENTITY
import com.crostage.trainchecker.ConstForTest.Companion.LIST_STATION_ENTITY
import com.crostage.trainchecker.data.model.station.StationEntity
import com.google.gson.Gson
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class StationListConverterTest {

    private val gson: Gson = mockk()
    val converter = StationListConverter()

    @Test
    fun testListToJson() {
        every { gson.toJson(LIST_STATION_ENTITY) } returns JSON_STATION_ENTITY
        val json = converter.listToJson(LIST_STATION_ENTITY)
        assertEquals(json, JSON_STATION_ENTITY)
    }

    @Test
    fun testJsonToList() {
        every {
            gson.fromJson(JSON_STATION_ENTITY, Array<StationEntity>::class.java).toList()
        } returns LIST_STATION_ENTITY
        val json = converter.jsonToList(JSON_STATION_ENTITY)
        assertEquals(json, LIST_STATION_ENTITY)
    }
}