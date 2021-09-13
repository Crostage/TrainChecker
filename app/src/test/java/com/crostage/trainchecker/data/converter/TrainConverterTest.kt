package com.crostage.trainchecker.data.converter

import com.crostage.trainchecker.data.converter.ConverterConst.Companion.TRAIN_ENTITY
import com.crostage.trainchecker.data.network.TestNetworkConst.Companion.TRAIN
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TrainConverterTest {
    private val converter = TrainConverter()

    @Test
    fun testConvert() {
        assert(TRAIN == converter.convert(TRAIN_ENTITY))
    }

    @Test
    fun testRevers() {
        assert(TRAIN_ENTITY == converter.revers(TRAIN))
    }

}