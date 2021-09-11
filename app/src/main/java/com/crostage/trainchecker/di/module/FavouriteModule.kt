package com.crostage.trainchecker.di.module

import com.crostage.trainchecker.data.converter.FavouriteConverter
import com.crostage.trainchecker.data.converter.IConverter
import com.crostage.trainchecker.data.db.TrainDatabase
import com.crostage.trainchecker.data.db.dao.TrainDao
import com.crostage.trainchecker.data.model.train.FavouriteEntity
import com.crostage.trainchecker.data.repository.TrainRepository
import com.crostage.trainchecker.domain.interactors.FavouriteInteractor
import com.crostage.trainchecker.domain.interactors.interfaces.IFavouriteInteractor
import com.crostage.trainchecker.domain.interactors.interfaces.ITrainInteractor
import com.crostage.trainchecker.domain.model.Train
import com.crostage.trainchecker.domain.repository.ITrainRepository
import com.crostage.trainchecker.utils.Constant.Companion.FAVOURITE
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module(includes = [FavouriteBindModule::class])
class FavouriteModule {

    @Provides
    fun provideTrainDao(database: TrainDatabase): TrainDao {
        return database.trainDao()
    }

}

@Module
interface FavouriteBindModule {

    @Binds
    @Named(FAVOURITE)
    fun bindFavouriteInteractorToIFavouriteInteractor(
        favouriteInteractor: FavouriteInteractor,
    ): IFavouriteInteractor

    @Binds
    fun bindITrainInteractorToIFavouriteInteractor(
        trainInteractor: ITrainInteractor,
    ): IFavouriteInteractor

    @Binds
    fun bindFavouriteServiceToIFavouriteService(trainRepository: TrainRepository): ITrainRepository

    @Binds
    fun bindFavouriteConverterToIConverter(
        favouriteConverter: FavouriteConverter,
    ): IConverter<FavouriteEntity, Train>
}
