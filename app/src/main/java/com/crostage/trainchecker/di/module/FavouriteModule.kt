package com.crostage.trainchecker.di.module

import com.crostage.trainchecker.data.network.services.RouteService
import com.crostage.trainchecker.data.repository.TrainRepository
import com.crostage.trainchecker.domain.interactors.FavouriteInteractor
import com.crostage.trainchecker.domain.interactors.RouteInteractor
import com.crostage.trainchecker.domain.interactors.interfaces.IFavouriteInteractor
import com.crostage.trainchecker.domain.interactors.interfaces.IRouteInteractor
import com.crostage.trainchecker.domain.network.IRouteService
import com.crostage.trainchecker.domain.repository.ITrainRepository
import dagger.Binds
import dagger.Module


@Module(includes = [FavouriteBindModule::class])
class FavouriteModule

@Module
interface FavouriteBindModule {
    @Binds
    fun bindRouteFavouriteToIFavouriteInteractor(favouriteInteractor: FavouriteInteractor): IFavouriteInteractor

    @Binds
    fun bindFavouriteServiceToIFavouriteService(trainRepository: TrainRepository): ITrainRepository


}