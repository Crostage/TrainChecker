package com.crostage.trainchecker.data.converter

import com.crostage.trainchecker.ConstForTest.Companion.LIST_TRAIN_STOP
import com.crostage.trainchecker.ConstForTest.Companion.LIST_TRAIN_STOP_DTO
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class StopListConverterTest {

    private val converter = StopListConverter()

    @Test
    fun testConvert() {
        assertEquals(LIST_TRAIN_STOP, converter.convert(LIST_TRAIN_STOP_DTO))
    }

    @Test
    fun testRevers() {
        assertEquals(LIST_TRAIN_STOP_DTO, converter.revers(LIST_TRAIN_STOP))
    }

}