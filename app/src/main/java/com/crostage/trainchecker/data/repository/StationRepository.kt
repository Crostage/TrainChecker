package com.crostage.trainchecker.data.repository

import com.crostage.trainchecker.data.db.dao.StationDao
import com.crostage.trainchecker.data.db.dao.StationResponseDao
import com.crostage.trainchecker.data.model.station.StationEntity
import com.crostage.trainchecker.data.model.station.StationSearchResponse
import com.crostage.trainchecker.domain.converter.IConverter
import com.crostage.trainchecker.domain.model.Station
import com.crostage.trainchecker.domain.repository.IStationRepository
import javax.inject.Inject

class StationRepository @Inject constructor(
    private val stationResponseDao: StationResponseDao,
    private val stationDao: StationDao,
    private val converter: IConverter<StationEntity, Station>,
    private val listConverter: IConverter<List<StationEntity>, List<Station>>,
) :
    IStationRepository {

    override fun insertStationResponse(name: String, stationList: List<Station>) {
        stationResponseDao.insertStationResponse(StationSearchResponse(name,
            listConverter.revers(stationList)))
    }

    override fun getListFromName(name: String): List<Station>? {
        return stationResponseDao.getListFromName(name)?.stationList
            ?.let { listConverter.convert(it) }
    }

    override fun insertStation(station: Station) {
        stationDao.insertStation(converter.revers(station))
    }

    override fun getLastStationsPick(): List<Station> {
        return listConverter.convert(stationDao.getLastStationPicks()).reversed()
    }

}