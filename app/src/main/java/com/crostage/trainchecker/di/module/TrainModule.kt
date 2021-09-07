package com.crostage.trainchecker.di.module

import com.crostage.trainchecker.data.converter.IConverter
import com.crostage.trainchecker.data.converter.TrainConverter
import com.crostage.trainchecker.data.db.TrainDao
import com.crostage.trainchecker.data.db.TrainDatabase
import com.crostage.trainchecker.data.model.train.TrainEntity
import com.crostage.trainchecker.data.network.services.TrainService
import com.crostage.trainchecker.data.repository.TrainRepository
import com.crostage.trainchecker.domain.interactors.FavouriteInteractor
import com.crostage.trainchecker.domain.interactors.TrainInteractor
import com.crostage.trainchecker.domain.interactors.interfaces.IFavouriteInteractor
import com.crostage.trainchecker.domain.interactors.interfaces.ITrainInteractor
import com.crostage.trainchecker.domain.model.Train
import com.crostage.trainchecker.domain.network.ITrainService
import com.crostage.trainchecker.domain.repository.ITrainRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [TrainBindModule::class])
class TrainModule {

    @Provides
    fun provideTrainDao(database: TrainDatabase): TrainDao {
        return database.trainDao()
    }


}

@Module
interface TrainBindModule {

    @Binds
    fun bindRouteFavouriteToIFavouriteInteractor(favouriteInteractor: FavouriteInteractor): IFavouriteInteractor

    @Binds
    fun bindFavouriteServiceToIFavouriteService(trainRepository: TrainRepository): ITrainRepository

    @Binds
    fun bindTrainInteractorToITrainInteractor(trainInteractor: TrainInteractor): ITrainInteractor

    @Binds
    fun bindTrainServiceToITrainService(trainService: TrainService): ITrainService

    @Binds
    fun bindTrainConverterToIConverter(trainConverter: TrainConverter): IConverter<TrainEntity, Train>

}