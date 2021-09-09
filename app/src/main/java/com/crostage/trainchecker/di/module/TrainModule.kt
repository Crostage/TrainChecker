package com.crostage.trainchecker.di.module

import com.crostage.trainchecker.data.converter.IConverter
import com.crostage.trainchecker.data.converter.TrainConverter
import com.crostage.trainchecker.data.model.train.TrainEntity
import com.crostage.trainchecker.data.network.services.TrainService
import com.crostage.trainchecker.domain.interactors.TrainInteractor
import com.crostage.trainchecker.domain.interactors.interfaces.IFavouriteInteractor
import com.crostage.trainchecker.domain.interactors.interfaces.ITrainInteractor
import com.crostage.trainchecker.domain.model.Train
import com.crostage.trainchecker.domain.network.ITrainService
import com.crostage.trainchecker.utils.Constant.Companion.TRAIN
import dagger.Binds
import dagger.Module
import javax.inject.Named

@Module(includes = [TrainBindModule::class])
class TrainModule

@Module
interface TrainBindModule {

    @Binds
    fun bindTrainInteractorToITrainInteractor(trainInteractor: TrainInteractor): ITrainInteractor

//    @Binds
//    fun bindTrainInteractorToIFavouriteInteractor(
//        trainInteractor: TrainInteractor,
//    ): IFavouriteInteractor


    @Binds
    fun bindTrainServiceToITrainService(trainService: TrainService): ITrainService

    @Binds
    fun bindTrainConverterToIConverter(trainConverter: TrainConverter): IConverter<TrainEntity, Train>


}