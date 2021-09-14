package com.crostage.trainchecker.domain.interactors.interfaces

import com.crostage.trainchecker.domain.model.Station
import io.reactivex.rxjava3.core.Single

interface IStationInteractor {

    fun getStationListFromRepo(name: String): List<Station>

    fun getStationListFromService(name: String): Single<List<Station>>

    fun insertStation(station: Station)

    fun getLastStationsPick(): List<Station>


}