package com.crostage.trainchecker.data.converter

import com.crostage.trainchecker.data.model.seat.CarDto
import com.crostage.trainchecker.domain.model.Car
import javax.inject.Inject

class CarListConverter @Inject constructor() :
    IConverter<@JvmSuppressWildcards List<CarDto>, @JvmSuppressWildcards List<Car>> {

    override fun convert(input: List<CarDto>): List<Car> {
        return input.map {
            Car(
                it.carNumber,
                it.carType,
                it.clsType,
                it.tariff,
                it.tickets
            )
        }
    }

    override fun revers(input: List<Car>): List<CarDto> {
        return input.map {
            CarDto(
                it.carNumber,
                it.carType,
                it.clsType,
                it.tariff,
                it.tickets
            )
        }
    }
}