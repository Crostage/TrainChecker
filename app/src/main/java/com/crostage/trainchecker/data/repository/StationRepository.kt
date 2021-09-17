package com.crostage.trainchecker.data.repository

import com.crostage.trainchecker.data.db.dao.StationDao
import com.crostage.trainchecker.data.db.dao.StationResponseDao
import com.crostage.trainchecker.data.model.station.StationEntity
import com.crostage.trainchecker.data.model.station.StationSearchResponse
import com.crostage.trainchecker.data.converter.IConverter
import com.crostage.trainchecker.domain.model.Station
import com.crostage.trainchecker.domain.repository.IStationRepository
import javax.inject.Inject

/**
 * Реализация [IStationRepository]
 *
 * @property stationResponseDao сущность для работы с поисковыми запросами станций
 * @property stationDao сущность для работы с последними выбранными станциями
 * @property converter конвертер из [StationEntity] -> [Station]
 * @property listConverter конвертер списков [StationEntity] -> [Station]
 */
class StationRepository @Inject constructor(
    private val stationResponseDao: StationResponseDao,
    private val stationDao: StationDao,
    private val converter: IConverter<StationEntity, Station>,
    private val listConverter: IConverter<List<StationEntity>, List<Station>>,
) :
    IStationRepository {

    /**
     * @see IStationRepository.insertStationResponse
     */
    override fun insertStationResponse(name: String, stationList: List<Station>) {
        stationResponseDao.insertStationResponse(StationSearchResponse(name,
            listConverter.revers(stationList)))
    }

    /**
     * @see IStationRepository.getListFromName
     */
    override fun getListFromName(name: String): List<Station>? {
        return stationResponseDao.getListFromName(name)?.stationList
            ?.let { listConverter.convert(it) }
    }

    /**
     * @see IStationRepository.insertStation
     */
    override fun insertStation(station: Station) {
        stationDao.insertStation(converter.revers(station))
    }

    /**
     * @see IStationRepository.getLastStationsPick
     */
    override fun getLastStationsPick(): List<Station> {
        return listConverter.convert(stationDao.getLastStationPicks()).reversed()
    }

}