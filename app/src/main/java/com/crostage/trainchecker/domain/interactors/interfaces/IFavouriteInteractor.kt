package com.crostage.trainchecker.domain.interactors.interfaces

import androidx.lifecycle.LiveData
import com.crostage.trainchecker.model.data.train.Train

interface IFavouriteInteractor {
    fun getTrainList(): LiveData<List<Train>>

    fun insertTrain(train: Train)

    fun removeTrain(train: Train)
}