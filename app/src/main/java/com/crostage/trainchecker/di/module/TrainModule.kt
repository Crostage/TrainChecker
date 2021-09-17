package com.crostage.trainchecker.di.module

import com.crostage.trainchecker.data.converter.IConverter
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

/**
 * Модуль для предоставления зависимостей для работы с поиском поездов
 *
 */
@Module(includes = [TrainBindModule::class])
class TrainModule

/**
 * Вспомогательный модуль для предоставления зависимостей для работы с поиском поездов
 */
@Module
interface TrainBindModule {

    /**
     * Предоставляет [TrainInteractor] в качестве [ITrainInteractor]
     *
     */
    @Binds
    fun bindTrainInteractorToITrainInteractor(trainInteractor: TrainInteractor): ITrainInteractor

    /**
     * Предоставляет [TrainService] в качестве [ITrainService]
     *
     */
    @Binds
    fun bindTrainServiceToITrainService(trainService: TrainService): ITrainService

    /**
     * Предоставляет [TrainConverter] в качестве [IConverter]
     *
     */
    @Binds
    fun bindTrainConverterToIConverter(trainConverter: TrainConverter)
            : IConverter<TrainEntity, Train>

    /**
     * Предоставляет [TrainListConverter] в качестве [IConverter]
     *
     */
    @Binds
    fun bindTrainListConverterToIConverter(trainListConverter: TrainListConverter)
            : IConverter<List<TrainEntity>, List<Train>>


}

