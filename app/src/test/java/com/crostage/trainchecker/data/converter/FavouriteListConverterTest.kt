package com.crostage.trainchecker.data.converter

import com.crostage.trainchecker.ConstForTest.Companion.FAVOURITE_ENTITY
import com.crostage.trainchecker.ConstForTest.Companion.TRAIN
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavouriteListConverterTest {
    private val converter = FavouriteListConverter()

    @Test
    fun testConvert() {
        assert(listOf(TRAIN) == converter.convert(listOf(FAVOURITE_ENTITY)))
    }

    @Test
    fun testRevers() {
        assert(listOf(FAVOURITE_ENTITY) == converter.revers(listOf(TRAIN)))
    }

}