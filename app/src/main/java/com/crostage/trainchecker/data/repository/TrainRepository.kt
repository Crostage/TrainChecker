package com.crostage.trainchecker.data.repository

import androidx.lifecycle.LiveData
import com.crostage.trainchecker.domain.repository.ITrainRepository
import com.crostage.trainchecker.data.model.train.Train
import javax.inject.Inject

class TrainRepository @Inject constructor(private val trainDao: TrainDao) : ITrainRepository {

    override fun getTrainList(): LiveData<List<Train>> {
        return trainDao.getTrainList()
    }

    override fun insertTrain(train: Train) {
        trainDao.insertTrain(train)
    }

    override fun removeTrain(train: Train) {
        trainDao.removeTrain(train)
    }
}