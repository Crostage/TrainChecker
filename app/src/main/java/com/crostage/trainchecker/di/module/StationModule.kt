package com.crostage.trainchecker.di.module

import com.crostage.trainchecker.data.network.services.StationService
import com.crostage.trainchecker.data.repository.StationDao
import com.crostage.trainchecker.data.repository.TrainDatabase
import com.crostage.trainchecker.domain.interactors.StationInteractor
import com.crostage.trainchecker.domain.interactors.interfaces.IStationInteractor
import com.crostage.trainchecker.domain.network.IStationService
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [StationBindModule::class])
class StationModule {

    @Provides
    fun provideStationDao(database: TrainDatabase): StationDao {
        return database.stationDao()
    }

}


@Module
interface StationBindModule {
    @Binds
    fun bindStationInteractorToIStationInteractor(stationInteractor: StationInteractor): IStationInteractor

    @Binds
    fun bindStationServiceToIStationService(stationService: StationService): IStationService


}