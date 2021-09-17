package com.crostage.trainchecker.di.module

import com.crostage.trainchecker.data.converter.CarListConverter
import com.crostage.trainchecker.data.converter.IConverter
import com.crostage.trainchecker.data.model.seat.CarDto
import com.crostage.trainchecker.data.network.services.SeatService
import com.crostage.trainchecker.domain.interactors.SeatInteractor
import com.crostage.trainchecker.domain.interactors.interfaces.ISeatInteractor
import com.crostage.trainchecker.domain.model.Car
import com.crostage.trainchecker.domain.network.ISeatService
import dagger.Binds
import dagger.Module

/**
 * Модуль для предоставления зависимостей для работы с вагонами
 *
 */
@Module(includes = [CarBindModule::class])
class CarModule


/**
 * Вспомогательный модуль для предоставления зависимостей для работы с вагонами
 */
@Module
interface CarBindModule {
    /**
     * Предоставляет [SeatInteractor] в качестве [ISeatInteractor]
     *
     */
    @Binds
    fun bindSeatInteractorToISeatInteractor(seatInteractor: SeatInteractor): ISeatInteractor

    /**
     * Предоставляет [SeatService] в качестве [ISeatService]
     *
     */
    @Binds
    fun bindSeatServiceToISeatService(seatService: SeatService): ISeatService

    /**
     * Предоставляет [CarListConverter] в качестве [IConverter]
     *
     */
    @Binds
    fun bindCarListConverterToIConverter(carListConverter: CarListConverter)
            : IConverter<List<CarDto>, List<Car>>
}