package com.crostage.trainchecker.di.module

import com.crostage.trainchecker.domain.converter.IConverter
import com.crostage.trainchecker.data.converter.TrainConverter
import com.crostage.trainchecker.data.converter.TrainListConverter
import com.crostage.trainchecker.data.model.train.TrainEntity
import com.crostage.trainchecker.data.network.services.TrainService
import com.crostage.trainchecker.domain.interactors.TrainInteractor
import com.crostage.trainchecker.domain.interactors.interfaces.ITrainInteractor
import com.crostage.trainchecker.domain.model.Train
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

    @Binds
    fun bindTrainConverterToIConverter(trainConverter: TrainConverter)
            : IConverter<TrainEntity, Train>

    @Binds
    fun bindTrainListConverterToIConverter(trainListConverter: TrainListConverter)
            : IConverter<List<TrainEntity>, List<Train>>


}

