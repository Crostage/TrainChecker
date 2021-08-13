package com.crostage.trainchecker.domain.network

import com.crostage.trainchecker.model.station.Station

interface IStationService {

    fun getStationList(stationName: String): List<Station>

}