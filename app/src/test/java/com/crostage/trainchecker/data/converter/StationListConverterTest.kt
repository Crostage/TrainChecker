package com.crostage.trainchecker.data.converter

import com.crostage.trainchecker.data.converter.ConverterConst.Companion.STATION
import com.crostage.trainchecker.data.converter.ConverterConst.Companion.STATION_ENTITY
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class StationListConverterTest {
    private val converter = StationListConverter()

    @Test
    fun testConvert() {
        assert(listOf(STATION) == converter.convert(listOf(STATION_ENTITY)))
    }

    @Test
    fun testRevers() {
        assert(listOf(STATION_ENTITY) == converter.revers(listOf(STATION)))
    }

}