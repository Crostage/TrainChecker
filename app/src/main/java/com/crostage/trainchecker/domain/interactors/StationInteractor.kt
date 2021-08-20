package com.crostage.trainchecker.domain.interactors

import com.crostage.trainchecker.data.repository.StationRepository
import com.crostage.trainchecker.domain.interactors.interfaces.IStationInteractor
import com.crostage.trainchecker.domain.network.IStationService
import com.crostage.trainchecker.model.data.station.Station
import com.crostage.trainchecker.model.domain.StationSearchResponse
import javax.inject.Inject

class StationInteractor @Inject constructor(
    private val service: IStationService,
    private val repository: StationRepository,
) :
    IStationInteractor {

    override fun getStationList(name: String): List<Station> {

        var searchResponse = repository.getListFromName(name)

        var list = searchResponse?.stationList
        if (list == null) {
            searchResponse = StationSearchResponse(name, getStationsFromNetwork(name))
            repository.insertStationResponse(searchResponse)
            list = searchResponse.stationList
        }
        return list
    }


    private fun getStationsFromNetwork(name: String): List<Station> =
        service.getStationList(name)

}