package com.crostage.trainchecker.di.module

import com.crostage.trainchecker.data.network.services.SeatService
import com.crostage.trainchecker.domain.interactors.SeatInteractor
import com.crostage.trainchecker.domain.interactors.interfaces.ISeatInteractor
import com.crostage.trainchecker.domain.network.ISeatService
import dagger.Binds
import dagger.Module


@Module(includes = [SeatBindModule::class])
class SeatModule


@Module
interface SeatBindModule {
    @Binds
    fun bindSeatInteractorToISeatenteractor(seatInteractor: SeatInteractor): ISeatInteractor

    @Binds
    fun bindSeateerviceToISeateervice(seatService: SeatService): ISeatService


}