package com.crostage.trainchecker.domain.interactors

import androidx.lifecycle.LiveData
import com.crostage.trainchecker.data.repository.TrainRepository
import com.crostage.trainchecker.domain.interactors.interfaces.IFavouriteInteractor
import com.crostage.trainchecker.model.data.train.TrainEntity
import com.crostage.trainchecker.model.domain.Train
import javax.inject.Inject


/**
 * Реализация [IFavouriteInteractor]
 *
 * @property trainRepository репо для получения [TrainEntity] данных
 */

class FavouriteInteractor @Inject constructor(
    private val trainRepository: TrainRepository,
) : IFavouriteInteractor {

    override fun getFavouriteTrainList(): LiveData<List<Train>> {
        return trainRepository.getFavouriteLiveData()
    }

    override fun removeTrain(train: Train) {
        trainRepository.removeTrain(train)
    }
}