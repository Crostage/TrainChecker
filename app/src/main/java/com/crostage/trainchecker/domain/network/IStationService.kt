package com.crostage.trainchecker.domain.network

import com.crostage.trainchecker.model.data.station.Station

interface IStationService {

    fun getStationList(stationName: String): List<Station>

}