package com.crostage.trainchecker.data.repository

import com.crostage.trainchecker.domain.repository.ITrainRepository
import com.crostage.trainchecker.model.domain.StationSearchResponse

class TrainRepository(private val trainDao: TrainDao) : ITrainRepository {

    override fun insertStationResponse(response: StationSearchResponse) {
        trainDao.insertStationResponse(response)
    }

    override fun getListFromName(name: String): StationSearchResponse? {
        return trainDao.getListFromName(name)
    }


}