package com.crostage.trainchecker.data.repository

import com.crostage.trainchecker.data.db.StationDao
import com.crostage.trainchecker.data.db.StationResponseDao
import com.crostage.trainchecker.data.model.station.Station
import com.crostage.trainchecker.data.model.station.StationSearchResponse
import com.crostage.trainchecker.domain.repository.IStationRepository
import javax.inject.Inject

class StationRepository @Inject constructor(
    private val stationResponseDao: StationResponseDao,
    private val stationDao: StationDao,
) :
    IStationRepository {

    override fun insertStationResponse(response: StationSearchResponse) {
        stationResponseDao.insertStationResponse(response)
    }

    override fun getListFromName(name: String): StationSearchResponse? {
        return stationResponseDao.getListFromName(name)
    }

    override fun insertStation(station: Station) {
        stationDao.insertStation(station)
    }

    override fun getLastStationsPick(): List<Station> {
        return stationDao.getLastStationPicks()
    }

}