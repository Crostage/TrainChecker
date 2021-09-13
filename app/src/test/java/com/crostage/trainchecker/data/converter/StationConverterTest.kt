package com.crostage.trainchecker.data.converter

import com.crostage.trainchecker.data.converter.ConverterConst.Companion.STATION
import com.crostage.trainchecker.data.converter.ConverterConst.Companion.STATION_ENTITY
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class StationConverterTest {

    private val converter = StationConverter()

    @Test
    fun testConvert() {
        assert(STATION == converter.convert(STATION_ENTITY))
    }

    @Test
    fun testRevers() {
        assert(STATION_ENTITY == converter.revers(STATION))
    }

}