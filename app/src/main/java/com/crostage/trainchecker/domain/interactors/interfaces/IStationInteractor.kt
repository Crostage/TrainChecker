package com.crostage.trainchecker.domain.interactors.interfaces

import com.crostage.trainchecker.domain.model.Station

interface IStationInteractor {

    fun getStationListFromRepo(name: String): List<Station>

    fun getStationListFromService(name: String): List<Station>

    fun insertStation(station: Station)

    fun getLastStationsPick(): List<Station>


}