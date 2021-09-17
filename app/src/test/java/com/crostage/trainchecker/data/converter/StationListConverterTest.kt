package com.crostage.trainchecker.data.converter

import com.crostage.trainchecker.ConstForTest.Companion.LIST_STATION
import com.crostage.trainchecker.ConstForTest.Companion.LIST_STATION_ENTITY
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class StationListConverterTest {
    private val converter = StationListConverter()

    @Test
    fun testConvert() {
        assertEquals(LIST_STATION, converter.convert(LIST_STATION_ENTITY))
    }

    @Test
    fun testRevers() {
        assertEquals(LIST_STATION_ENTITY, converter.revers(LIST_STATION))
    }

}