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

/**
 * Модуль для предоставления зависимостей для работы со станциями
 *
 */
@Module(includes = [StationBindModule::class])
class StationModule {

    /**
     * Предоставляет [StationResponseDao]
     *
     * @param database [TrainDatabase] класс базы данных
     * @return [StationResponseDao] класс для работы с таблицей запросв поиска станций
     */
    @Provides
    @StationScope
    fun provideStationResponseDao(database: TrainDatabase): StationResponseDao {
        return database.stationResponseDao()
    }

    /**
     * Предоставляет [StationDao]
     *
     * @param database [TrainDatabase] класс базы данных
     * @return [StationDao] класс для работы с таблицей последних станций
     */
    @Provides
    @StationScope
    fun provideStationDao(database: TrainDatabase): StationDao {
        return database.lastStationsDao()
    }
}

/**
 * Вспомогательный модуль для предоставления зависимостей для работы со станциями
 */
@Module
interface StationBindModule {

    /**
     * Предоставляет [StationInteractor] в качестве [IStationInteractor]
     *
     */
    @Binds
    fun bindStationInteractorToIStationInteractor(stationInteractor: StationInteractor)
            : IStationInteractor

    /**
     * Предоставляет [StationService] в качестве [IStationService]
     *
     */
    @Binds
    fun bindStationServiceToIStationService(stationService: StationService): IStationService

    /**
     * Предоставляет [StationRepository] в качестве [IStationRepository]
     *
     */
    @Binds
    fun bindStationRepositoryToIStationRepository(stationRepo: StationRepository)
            : IStationRepository

    /**
     * Предоставляет [StationConverter] в качестве [IConverter]
     *
     */
    @Binds
    fun bindStationConverterToIConverter(stationConverter: StationConverter)
            : IConverter<StationEntity, Station>

    /**
     * Предоставляет [StationListConverter] в качестве [IConverter]
     *
     */
    @Binds
    fun bindStationListConverterToIConverter(stationListConverter: StationListConverter)
            : IConverter<List<StationEntity>, List<Station>>


}
