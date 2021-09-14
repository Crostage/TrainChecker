package com.crostage.trainchecker.data.converter

import com.crostage.trainchecker.data.converter.ConverterConst.Companion.TRAIN_ENTITY
import com.crostage.trainchecker.data.network.TestNetworkConst.Companion.TRAIN
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TrainListConverterTest {
    private val converter = TrainListConverter()

    @Test
    fun testConvert() {
        assert(listOf(TRAIN) == converter.convert(listOf(TRAIN_ENTITY)))
    }

    @Test
    fun testRevers() {
        assert(listOf(TRAIN_ENTITY) == converter.revers(listOf(TRAIN)))
    }

}