package com.crostage.trainchecker.data.repository

import androidx.lifecycle.LiveData
import com.crostage.trainchecker.data.model.stationRequest.Station
import com.crostage.trainchecker.data.model.trainRequest.Train

class TrainRepoImp(private val trainDao: TrainDao) : TrainRepository {

    override suspend fun getStationList(): List<Station>{
        return trainDao.getStationList()
    }

    override suspend fun insertStation(station: Station) {
        trainDao.insertStation(station)
    }

    override suspend fun getTrainList(): LiveData<List<Train>> {
        return trainDao.getTrainList()
    }


}