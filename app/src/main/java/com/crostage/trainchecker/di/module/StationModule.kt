package com.crostage.trainchecker.di.module

import com.crostage.trainchecker.data.converter.IConverter
import com.crostage.trainchecker.data.converter.StationConverter
import com.crostage.trainchecker.data.converter.StationListConverter
import com.crostage.trainchecker.data.db.TrainDatabase
import com.crostage.trainchecker.data.db.dao.StationDao
import com.crostage.trainchecker.data.db.dao.StationResponseDao
import com.crostage.trainchecker.data.model.station.StationEntity
import com.crostage.trainchecker.data.network.services.StationService
import com.crostage.trainchecker.data.repository.StationRepository
import com.crostage.trainchecker.di.component.StationScope
import com.crostage.trainchecker.domain.interactors.StationInteractor
import com.crostage.trainchecker.domain.interactors.interfaces.IStationInteractor
import com.crostage.trainchecker.domain.model.Station
import com.crostage.trainchecker.domain.network.IStationService
import com.crostage.trainchecker.domain.repository.IStationRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [StationBindModule::class])
class StationModule {

    @Provides
    @StationScope
    fun provideStationResponseDao(database: TrainDatabase): StationResponseDao {
        return database.stationResponseDao()
    }

    @Provides
    @StationScope
    fun provideStationDao(database: TrainDatabase): StationDao {
        return database.lastStationsDao()
    }
}

@Module
interface StationBindModule {
    @Binds
    fun bindStationInteractorToIStationInteractor(stationInteractor: StationInteractor)
            : IStationInteractor

    @Binds
    fun bindStationServiceToIStationService(stationService: StationService): IStationService

    @Binds
    fun bindStationRepositoryToIStationRepository(stationRepo: StationRepository)
            : IStationRepository

    @Binds
    fun bindStationConverterToIConverter(stationConverter: StationConverter)
            : IConverter<StationEntity, Station>

    @Binds
    fun bindStationListConverterToIConverter(stationListConverter: StationListConverter)
            : IConverter<List<StationEntity>, List<Station>>


}
