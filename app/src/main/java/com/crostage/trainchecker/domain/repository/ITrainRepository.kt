package com.crostage.trainchecker.domain.repository

import androidx.lifecycle.LiveData
import com.crostage.trainchecker.model.data.train.Train

interface ITrainRepository {
    fun getTrainList(): LiveData<List<Train>>

    fun insertTrain(train: Train)

    fun removeTrain(train: Train)
}