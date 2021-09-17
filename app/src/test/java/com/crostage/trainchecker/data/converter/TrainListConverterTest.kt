package com.crostage.trainchecker.data.converter

import com.crostage.trainchecker.ConstForTest.Companion.LIST_TRAIN
import com.crostage.trainchecker.ConstForTest.Companion.LIST_TRAIN_ENTITY
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class TrainListConverterTest {
    private val converter = TrainListConverter()

    @Test
    fun testConvert() {
        assertEquals(LIST_TRAIN, converter.convert(LIST_TRAIN_ENTITY))
    }

    @Test
    fun testRevers() {
        assertEquals(LIST_TRAIN_ENTITY, converter.revers(LIST_TRAIN))
    }

}