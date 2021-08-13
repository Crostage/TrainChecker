package com.crostage.trainchecker.domain.interactors.interfaces

import com.crostage.trainchecker.model.data.station.Station

interface IStationInteractor {

    fun getStationList(name: String): List<Station>

}