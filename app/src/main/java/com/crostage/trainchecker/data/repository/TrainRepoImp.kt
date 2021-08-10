package com.crostage.trainchecker.data.repository

import com.crostage.trainchecker.data.model.stationRequest.Station

class TrainRepoImp(private val trainDao: TrainDao) : TrainRepository {

    override  fun getStationList(): List<Station>{
        return trainDao.getStationList()
    }

    override  fun insertStation(station: Station) {
        trainDao.insertStation(station)
    }

//    override suspend fun getTrainList(): LiveData<List<Train>> {
//        return trainDao.getTrainList()
//    }


}