package com.crostage.trainchecker.data.repository

import androidx.lifecycle.LiveData
import com.crostage.trainchecker.data.db.TrainDao
import com.crostage.trainchecker.domain.repository.ITrainRepository
import com.crostage.trainchecker.model.data.train.TrainEntity
import javax.inject.Inject

class TrainRepository @Inject constructor(private val trainDao: TrainDao) : ITrainRepository {

    override fun getTrainList(): LiveData<List<TrainEntity>> {
        return trainDao.getTrainList()
    }

    override fun insertTrain(train: TrainEntity) {
        trainDao.insertTrain(train)
    }

    override fun removeTrain(train: TrainEntity) {
        trainDao.removeTrain(train)
    }
}