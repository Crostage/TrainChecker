package com.crostage.trainchecker.domain.interactors

import com.crostage.trainchecker.domain.interactors.interfaces.IStationInteractor
import com.crostage.trainchecker.domain.model.Station
import com.crostage.trainchecker.domain.network.IStationService
import com.crostage.trainchecker.domain.repository.IStationRepository
import io.reactivex.rxjava3.core.Single
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

    /**
     * @see IStationInteractor.getStationListFromRepo
     */
    override fun getStationListFromRepo(name: String): List<Station> {
        return repository.getListFromName(name) ?: listOf()
    }

    /**
     * @see IStationInteractor.getStationListFromService
     */
    override fun getStationListFromService(name: String): Single<List<Station>> {
        return Single.fromCallable {
            service.getStationList(name)
        }.flatMap {
            if (it.isNotEmpty()) {
                repository.insertStationResponse(name, it)
            }
            Single.just(it ?: listOf())
        }

    }

    /**
     * @see IStationInteractor.insertStation
     */
    override fun insertStation(station: Station) {
        repository.insertStation(station)
    }

    /**
     * @see IStationInteractor.getLastStationsPick
     */
    override fun getLastStationsPick(): List<Station> {
        return repository.getLastStationsPick()
    }

}