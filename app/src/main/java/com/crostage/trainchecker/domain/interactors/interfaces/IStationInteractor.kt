package com.crostage.trainchecker.domain.interactors.interfaces

import com.crostage.trainchecker.data.model.station.Station

interface IStationInteractor {

    fun getStationList(name: String): List<Station>

    fun insertStation(station: Station)

    fun getLastStationsPick(): List<Station>

}