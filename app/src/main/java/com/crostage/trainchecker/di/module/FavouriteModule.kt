package com.crostage.trainchecker.di.module

import com.crostage.trainchecker.data.db.TrainDao
import com.crostage.trainchecker.data.db.TrainDatabase
import com.crostage.trainchecker.data.repository.TrainRepository
import com.crostage.trainchecker.domain.interactors.FavouriteInteractor
import com.crostage.trainchecker.domain.interactors.interfaces.IFavouriteInteractor
import com.crostage.trainchecker.domain.repository.ITrainRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

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
    fun bindRouteFavouriteToIFavouriteInteractor(favouriteInteractor: FavouriteInteractor): IFavouriteInteractor

    @Binds
    fun bindFavouriteServiceToIFavouriteService(trainRepository: TrainRepository): ITrainRepository
}
