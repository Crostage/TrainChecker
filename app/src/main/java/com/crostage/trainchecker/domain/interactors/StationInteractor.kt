package com.crostage.trainchecker.domain.interactors

import com.crostage.trainchecker.data.converter.IConverter
import com.crostage.trainchecker.domain.interactors.interfaces.IStationInteractor
import com.crostage.trainchecker.domain.network.IStationService
import com.crostage.trainchecker.domain.repository.IStationRepository
import com.crostage.trainchecker.model.data.station.StationEntity
import com.crostage.trainchecker.model.data.station.StationSearchResponse
import com.crostage.trainchecker.model.domain.Station
import javax.inject.Inject


/**
 * Реализация [IStationInteractor]
 *
 * @property service сервис для получения [Station] данных из сети
 * @property repository репо для получения [Station] данных из кэша (БД)
 */
class StationInteractor @Inject constructor(
    private val service: IStationService,
    private val repository: IStationRepository,
    private val converter: IConverter<StationEntity, Station>,
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
        return list.map { converter.convert(it) }
    }

    override fun insertStation(station: Station) {
        repository.insertStation(converter.revers(station))
    }

    override fun getLastStationsPick(): List<Station> {

        return repository.getLastStationsPick().map { converter.convert(it) }
    }


    private fun getStationsFromNetwork(name: String): List<StationEntity> =
        service.getStationList(name)
}