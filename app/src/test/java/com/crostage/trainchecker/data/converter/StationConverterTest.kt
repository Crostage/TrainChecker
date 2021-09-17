package com.crostage.trainchecker.data.converter

import com.crostage.trainchecker.ConstForTest.Companion.STATION
import com.crostage.trainchecker.ConstForTest.Companion.STATION_ENTITY
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class StationConverterTest {

    private val converter = StationConverter()

    @Test
    fun testConvert() {
        assertEquals(STATION, converter.convert(STATION_ENTITY))
    }

    @Test
    fun testRevers() {
        assertEquals(STATION_ENTITY, converter.revers(STATION))
    }

}