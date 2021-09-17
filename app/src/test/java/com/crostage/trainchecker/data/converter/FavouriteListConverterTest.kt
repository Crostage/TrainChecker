package com.crostage.trainchecker.data.converter

import com.crostage.trainchecker.ConstForTest.Companion.LIST_FAVOURITE_ENTITY
import com.crostage.trainchecker.ConstForTest.Companion.LIST_TRAIN
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class FavouriteListConverterTest {
    private val converter = FavouriteListConverter()

    @Test
    fun testConvert() {
        assertEquals(LIST_TRAIN, converter.convert(LIST_FAVOURITE_ENTITY))
    }

    @Test
    fun testRevers() {
        assertEquals(LIST_FAVOURITE_ENTITY, converter.revers(LIST_TRAIN))
    }

}