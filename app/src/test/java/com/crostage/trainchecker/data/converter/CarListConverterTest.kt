package com.crostage.trainchecker.data.converter


import com.crostage.trainchecker.ConstForTest.Companion.LIST_CAR
import com.crostage.trainchecker.ConstForTest.Companion.LIST_CAR_DTO
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class CarListConverterTest {

    private val converter = CarListConverter()

    @Test
    fun testConvert() {
        assertEquals(LIST_CAR, converter.convert(LIST_CAR_DTO))
    }

    @Test
    fun testRevers() {
        assertEquals(LIST_CAR_DTO, converter.revers(LIST_CAR))
    }


}