package com.crostage.trainchecker.data.repository

import com.crostage.trainchecker.data.converter.IConverter
import com.crostage.trainchecker.data.db.StationDao
import com.crostage.trainchecker.data.db.StationResponseDao
import com.crostage.trainchecker.domain.repository.IStationRepository
import com.crostage.trainchecker.data.model.station.StationEntity
import com.crostage.trainchecker.data.model.station.StationSearchResponse
import com.crostage.trainchecker.domain.model.Station
import javax.inject.Inject

class StationRepository @Inject constructor(
    private val stationResponseDao: StationResponseDao,
    private val stationDao: StationDao,
    private val converter: IConverter<StationEntity, Station>,
) :
    IStationRepository {

    override fun insertStationResponse(name: String, stationList: List<Station>) {
        stationResponseDao.insertStationResponse(StationSearchResponse(name,
            stationList.map { converter.revers(it) }))
    }

    override fun getListFromName(name: String): List<Station>? {
        return stationResponseDao.getListFromName(name)?.stationList?.map { converter.convert(it) }
    }

    override fun insertStation(station: Station) {
        stationDao.insertStation(converter.revers(station))
    }

    override fun getLastStationsPick(): List<Station> {
        return stationDao.getLastStationPicks().map { converter.convert(it) }
    }

}