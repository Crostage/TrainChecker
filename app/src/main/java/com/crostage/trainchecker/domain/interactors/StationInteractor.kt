package com.crostage.trainchecker.domain.interactors

import com.crostage.trainchecker.domain.interactors.interfaces.IStationInteractor
import com.crostage.trainchecker.domain.model.Station
import com.crostage.trainchecker.domain.network.IStationService
import com.crostage.trainchecker.domain.repository.IStationRepository
import io.reactivex.rxjava3.core.Completable
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

    override fun getStationListFromRepo(name: String): List<Station> {
        return repository.getListFromName(name) ?: listOf()
    }

    override fun getStationListFromService(name: String): Single<List<Station>> {
        //todo поправить тест
        return Single.fromCallable {
            service.getStationList(name)
        }.flatMap {
            if (it != null && it.isNotEmpty()) {
                Completable.fromCallable {
                    repository.insertStationResponse(name, it)
                }
            }
            Single.just(it ?: listOf())

        }

    }

    override fun insertStation(station: Station) {
        repository.insertStation(station)
    }

    override fun getLastStationsPick(): List<Station> {
        return repository.getLastStationsPick()
    }

}