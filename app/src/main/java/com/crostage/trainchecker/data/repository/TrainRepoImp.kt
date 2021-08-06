package com.crostage.trainchecker.data.repository

import com.crostage.trainchecker.data.model.stationRequest.Station

class TrainRepoImp(private val trainDao: TrainDao) : TrainRepository {

    override suspend fun getStationList(): List<Station>{
        return trainDao.getStationList()
    }

    override suspend fun insertStation(station: Station) {
        trainDao.insertStation(station)
    }

//    override suspend fun getTrainList(): LiveData<List<Train>> {
//        return trainDao.getTrainList()
//    }


}