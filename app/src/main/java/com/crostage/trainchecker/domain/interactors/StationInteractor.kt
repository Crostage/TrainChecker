package com.crostage.trainchecker.domain.interactors

import com.crostage.trainchecker.domain.interactors.interfaces.IStationInteractor
import com.crostage.trainchecker.domain.model.Station
import com.crostage.trainchecker.domain.network.IStationService
import com.crostage.trainchecker.domain.repository.IStationRepository
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
) :
    IStationInteractor {

    override fun getStationListFromRepo(name: String): List<Station> {
        return repository.getListFromName(name) ?: listOf()
    }

    override fun getStationListFromService(name: String): List<Station> {
        val list = getStationsFromNetwork(name)
        list?.let {
            if (it.isNotEmpty()) repository.insertStationResponse(name, it)
        }
        return list ?: listOf()
    }

    override fun insertStation(station: Station) {
        repository.insertStation(station)
    }

    override fun getLastStationsPick(): List<Station> {
        return repository.getLastStationsPick()
    }

    private fun getStationsFromNetwork(name: String): List<Station>? =
        service.getStationList(name)
}