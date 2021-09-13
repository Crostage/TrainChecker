package com.crostage.trainchecker.data.converter

import com.crostage.trainchecker.data.converter.ConverterConst.Companion.TRAIN_STOP
import com.crostage.trainchecker.data.converter.ConverterConst.Companion.TRAIN_STOP_DTO
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TrainStopListConverterTest {

    private val converter = TrainStopListConverter()

    @Test
    fun testConvert() {
        assert(listOf(TRAIN_STOP) == converter.convert(listOf(TRAIN_STOP_DTO)))
    }

    @Test
    fun testRevers() {
        assert(listOf(TRAIN_STOP_DTO) == converter.revers(listOf(TRAIN_STOP)))
    }

}