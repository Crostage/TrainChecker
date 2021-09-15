package com.crostage.trainchecker.data.converter

import com.crostage.trainchecker.ConstForTest.Companion.TRAIN_STOP
import com.crostage.trainchecker.ConstForTest.Companion.TRAIN_STOP_DTO
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class StopListConverterTest {

    private val converter = StopListConverter()

    @Test
    fun testConvert() {
        assert(listOf(TRAIN_STOP) == converter.convert(listOf(TRAIN_STOP_DTO)))
    }

    @Test
    fun testRevers() {
        assert(listOf(TRAIN_STOP_DTO) == converter.revers(listOf(TRAIN_STOP)))
    }

}