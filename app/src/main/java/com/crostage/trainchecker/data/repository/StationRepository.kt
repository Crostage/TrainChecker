package com.crostage.trainchecker.data.repository

import com.crostage.trainchecker.domain.repository.IStationRepository
import com.crostage.trainchecker.data.model.station.StationSearchResponse
import javax.inject.Inject

class StationRepository @Inject constructor(private val stationDao: StationDao) :
    IStationRepository {

    override fun insertStationResponse(response: StationSearchResponse) {
        stationDao.insertStationResponse(response)
    }

    override fun getListFromName(name: String): StationSearchResponse? {
        return stationDao.getListFromName(name)
    }

}