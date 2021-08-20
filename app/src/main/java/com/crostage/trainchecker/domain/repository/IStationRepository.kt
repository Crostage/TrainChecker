package com.crostage.trainchecker.domain.repository

import com.crostage.trainchecker.model.domain.StationSearchResponse

interface IStationRepository {

    fun insertStationResponse(response: StationSearchResponse)

    fun getListFromName(name: String): StationSearchResponse?

}