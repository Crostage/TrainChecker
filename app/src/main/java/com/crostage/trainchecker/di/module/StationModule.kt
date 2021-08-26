package com.crostage.trainchecker.di.module

import com.crostage.trainchecker.data.db.StationDao
import com.crostage.trainchecker.data.db.StationResponseDao
import com.crostage.trainchecker.data.db.TrainDatabase
import com.crostage.trainchecker.data.network.services.StationService
import com.crostage.trainchecker.data.repository.StationRepository
import com.crostage.trainchecker.domain.interactors.StationInteractor
import com.crostage.trainchecker.domain.interactors.interfaces.IStationInteractor
import com.crostage.trainchecker.domain.network.IStationService
import com.crostage.trainchecker.domain.repository.IStationRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [StationBindModule::class])
class StationModule {

    @Provides
    fun provideStationResponseDao(database: TrainDatabase): StationResponseDao {
        return database.stationResponseDao()
    }

    @Provides
    fun provideStationDao(database: TrainDatabase): StationDao {
        return database.lastStationsDao()
    }
}


@Module
interface StationBindModule {
    @Binds
    fun bindStationInteractorToIStationInteractor(stationInteractor: StationInteractor): IStationInteractor

    @Binds
    fun bindStationServiceToIStationService(stationService: StationService): IStationService

    @Binds
    fun bindStationRepositoryToIStationRepository(stationRepo: StationRepository): IStationRepository


}