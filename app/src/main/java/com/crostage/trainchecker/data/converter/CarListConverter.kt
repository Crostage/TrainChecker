package com.crostage.trainchecker.data.converter

import com.crostage.trainchecker.data.model.seat.CarDto
import com.crostage.trainchecker.domain.model.Car
import javax.inject.Inject

/**
 * @see IConverter конвертер для вагонов
 */
class CarListConverter @Inject constructor() :
    IConverter<@JvmSuppressWildcards List<CarDto>, @JvmSuppressWildcards List<Car>> {

    /**
     * @see IConverter.convert
     */
    override fun convert(input: List<CarDto>): List<Car> {
        return input.map {
            Car(
                it.carNumber,
                it.carType,
                it.clsType,
                it.tariff,
                it.seats
            )
        }
    }

    /**
     * @see IConverter.revers
     */
    override fun revers(input: List<Car>): List<CarDto> {
        return input.map {
            CarDto(
                it.carNumber,
                it.carType,
                it.clsType,
                it.tariff,
                it.seats
            )
        }
    }
}