package com.crostage.trainchecker.domain.repository

import com.crostage.trainchecker.model.data.station.Station
import com.crostage.trainchecker.model.domain.StationSearchResponse

interface ITrainRepository {

    fun insertStationResponse(response: StationSearchResponse)

    fun getListFromName(name: String): StationSearchResponse?

}