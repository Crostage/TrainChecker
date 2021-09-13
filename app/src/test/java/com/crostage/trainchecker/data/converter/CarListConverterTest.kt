package com.crostage.trainchecker.data.converter


import com.crostage.trainchecker.data.converter.ConverterConst.Companion.CAR
import com.crostage.trainchecker.data.converter.ConverterConst.Companion.CAR_DTO
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CarListConverterTest {

    private val converter = CarListConverter()

    @Test
    fun testConvert() {
        assert(listOf(CAR) == converter.convert(listOf(CAR_DTO)))
    }

    @Test
    fun testRevers() {
        assert(listOf(CAR_DTO) == converter.revers(listOf(CAR)))
    }


}