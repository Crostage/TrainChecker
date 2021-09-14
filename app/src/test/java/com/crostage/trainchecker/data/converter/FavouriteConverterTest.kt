package com.crostage.trainchecker.data.converter

import com.crostage.trainchecker.data.converter.ConverterConst.Companion.FAVOURITE_ENTITY
import com.crostage.trainchecker.data.converter.ConverterConst.Companion.TRAIN
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavouriteConverterTest {
    private val converter = FavouriteConverter()


    @Test
    fun testConvert() {
        assert(TRAIN == converter.convert(FAVOURITE_ENTITY))
    }

    @Test
    fun testRevers() {
        assert(FAVOURITE_ENTITY == converter.revers(TRAIN))
    }

}