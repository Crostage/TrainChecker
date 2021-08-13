package com.crostage.trainchecker.data.repository

import com.crostage.trainchecker.domain.repository.ITrainRepository
import com.crostage.trainchecker.model.station.Station

class TrainRepository(private val trainDao: TrainDao) : ITrainRepository {

    override fun getStationList(): List<Station> {
        return trainDao.getStationList()
    }

    override fun insertStation(station: Station) {
        trainDao.insertStation(station)
    }

//    override suspend fun getTrainList(): LiveData<List<Train>> {
//        return trainDao.getTrainList()
//    }


}