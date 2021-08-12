package com.crostage.trainchecker.domain.interactors

import com.crostage.trainchecker.model.station.Station

interface IStationInteractor {

    fun getStationList(name: String): List<Station>

}