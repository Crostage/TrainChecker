package com.crostage.trainchecker.domain.interactors

import androidx.lifecycle.LiveData
import com.crostage.trainchecker.data.repository.TrainRepository
import com.crostage.trainchecker.domain.interactors.interfaces.IFavouriteInteractor
import com.crostage.trainchecker.model.data.train.Train
import javax.inject.Inject

class FavouriteInteractor @Inject constructor(
    private val trainRepository: TrainRepository,
) : IFavouriteInteractor {

    override fun getTrainList(): LiveData<List<Train>> {
        return trainRepository.getTrainList()
    }

    override fun insertTrain(train: Train) {
        trainRepository.insertTrain(train)
    }

    override fun removeTrain(train: Train) {
        trainRepository.removeTrain(train)
    }
}