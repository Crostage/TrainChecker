package com.crostage.trainchecker.data.network.services

import com.crostage.trainchecker.model.station.Station

interface IStationService {

    fun getStationCode(stationName: String): List<Station>?

}