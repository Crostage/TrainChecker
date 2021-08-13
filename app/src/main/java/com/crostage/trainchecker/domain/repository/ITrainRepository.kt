package com.crostage.trainchecker.domain.repository

import com.crostage.trainchecker.model.station.Station

interface ITrainRepository {

    fun getStationList(): List<Station>

    fun insertStation(station: Station)

//    suspend fun getTrainList(): LiveData<List<Train>>

//    suspend fun getStationCode(stationName: String): Int

}