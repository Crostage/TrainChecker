package com.crostage.trainchecker.di.module

import com.crostage.trainchecker.data.converter.CarListConverter
import com.crostage.trainchecker.domain.converter.IConverter
import com.crostage.trainchecker.data.network.services.SeatService
import com.crostage.trainchecker.domain.interactors.SeatInteractor
import com.crostage.trainchecker.domain.interactors.interfaces.ISeatInteractor
import com.crostage.trainchecker.domain.network.ISeatService
import com.crostage.trainchecker.data.model.seat.CarDto
import com.crostage.trainchecker.domain.model.Car
import dagger.Binds
import dagger.Module


@Module(includes = [SeatBindModule::class])
class SeatModule


@Module
interface SeatBindModule {
    @Binds
    fun bindSeatInteractorToISeatInteractor(seatInteractor: SeatInteractor): ISeatInteractor

    @Binds
    fun bindSeatServiceToISeatService(seatService: SeatService): ISeatService

    @Binds
    fun bindCarListConverterToIConverter(carListConverter: CarListConverter)
            : IConverter<List<CarDto>, List<Car>>
}