package com.crostage.trainchecker.data.converter

import com.crostage.trainchecker.ConstForTest.Companion.FAVOURITE_ENTITY
import com.crostage.trainchecker.ConstForTest.Companion.TRAIN
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class FavouriteConverterTest {
    private val converter = FavouriteConverter()


    @Test
    fun testConvert() {
        assertEquals(TRAIN, converter.convert(FAVOURITE_ENTITY))
    }

    @Test
    fun testRevers() {
        assertEquals(FAVOURITE_ENTITY, converter.revers(TRAIN))
    }

}