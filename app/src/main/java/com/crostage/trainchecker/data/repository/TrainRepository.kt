package com.crostage.trainchecker.data.repository

import androidx.lifecycle.LiveData
import com.crostage.trainchecker.data.model.stationRequest.Station
import com.crostage.trainchecker.data.model.trainRequest.Train

interface TrainRepository {

    suspend fun getStationList(): List<Station>

    suspend fun insertStation(station: Station)

//    suspend fun getTrainList(): LiveData<List<Train>>

//    suspend fun getStationCode(stationName: String): Int

}