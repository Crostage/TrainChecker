package com.crostage.trainchecker.data.network.services

import com.crostage.trainchecker.model.station.Station

interface IStationService {

    fun getStationList(stationName: String): List<Station>

}