package com.crostage.trainchecker.di.module

import com.crostage.trainchecker.data.network.services.TrainService
import com.crostage.trainchecker.domain.interactors.TrainInteractor
import com.crostage.trainchecker.domain.interactors.interfaces.ITrainInteractor
import com.crostage.trainchecker.domain.network.ITrainService
import dagger.Binds
import dagger.Module

@Module(includes = [TrainBindModule::class])
class TrainModule

@Module
interface TrainBindModule {
    @Binds
    fun bindTrainInteractorToITrainInteractor(trainInteractor: TrainInteractor): ITrainInteractor

    @Binds
    fun bindTrainServiceToITrainService(trainService: TrainService): ITrainService


}