package com.crostage.trainchecker.domain.interactors

import androidx.lifecycle.LiveData
import com.crostage.trainchecker.data.model.train.Train
import com.crostage.trainchecker.data.repository.TrainRepository
import com.crostage.trainchecker.domain.interactors.interfaces.IFavouriteInteractor
import javax.inject.Inject

/**
 * Реализация [IFavouriteInteractor]
 *
 * @property trainRepository репо для получения [Train] данных
 */

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