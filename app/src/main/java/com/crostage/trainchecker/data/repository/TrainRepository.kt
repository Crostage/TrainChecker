package com.crostage.trainchecker.data.repository

import com.crostage.trainchecker.model.station.Station

interface TrainRepository {

     fun getStationList(): List<Station>

     fun insertStation(station: Station)

//    suspend fun getTrainList(): LiveData<List<Train>>

//    suspend fun getStationCode(stationName: String): Int

}