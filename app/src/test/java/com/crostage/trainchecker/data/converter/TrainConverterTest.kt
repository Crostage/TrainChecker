package com.crostage.trainchecker.data.converter

import com.crostage.trainchecker.ConstForTest.Companion.TRAIN
import com.crostage.trainchecker.ConstForTest.Companion.TRAIN_ENTITY
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class TrainConverterTest {
    private val converter = TrainConverter()

    @Test
    fun testConvert() {
        assertEquals(TRAIN, converter.convert(TRAIN_ENTITY))
    }

    @Test
    fun testRevers() {
        assertEquals(TRAIN_ENTITY, converter.revers(TRAIN))
    }

}