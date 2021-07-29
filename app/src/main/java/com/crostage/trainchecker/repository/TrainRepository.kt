package com.crostage.trainchecker.repository

import androidx.lifecycle.LiveData
import com.crostage.trainchecker.model.stationRequest.Station
import com.crostage.trainchecker.model.trainRequest.Train

interface TrainRepository {

    suspend fun getStationList(): List<Station>

    suspend fun insertStation(station: Station)

    suspend fun getTrainList(): LiveData<List<Train>>


}