package com.crostage.trainchecker.domain.interactors

import androidx.lifecycle.LiveData
import com.crostage.trainchecker.domain.interactors.interfaces.IFavouriteInteractor
import com.crostage.trainchecker.domain.repository.ITrainRepository
import com.crostage.trainchecker.data.model.train.TrainEntity
import com.crostage.trainchecker.domain.model.Train
import javax.inject.Inject


/**
 * Реализация [IFavouriteInteractor]
 *
 * @property trainRepository репо для получения [TrainEntity] данных
 */

class FavouriteInteractor @Inject constructor(
    private val trainRepository: ITrainRepository,
) : IFavouriteInteractor {

    override fun getFavouriteLiveData(): LiveData<List<Train>> {
        return trainRepository.getFavouriteLiveData()
    }

    override fun getFavouriteList(): List<Train> {
        return trainRepository.getFavouriteList()
    }

    override fun removeTrain(train: Train) {
        trainRepository.removeTrain(train)
    }

    override fun insertTrain(train: Train) {
        trainRepository.insertTrain(train)
    }
}