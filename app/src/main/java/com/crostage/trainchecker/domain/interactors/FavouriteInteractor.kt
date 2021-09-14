package com.crostage.trainchecker.domain.interactors

import androidx.lifecycle.LiveData
import com.crostage.trainchecker.domain.interactors.interfaces.IFavouriteInteractor
import com.crostage.trainchecker.domain.model.Train
import com.crostage.trainchecker.domain.repository.ITrainRepository
import javax.inject.Inject


/**
 * Реализация [IFavouriteInteractor]
 *
 * @property trainRepository репо для получения [Train] данных
 */

open class FavouriteInteractor @Inject constructor(
    private val trainRepository: ITrainRepository,
) : IFavouriteInteractor {

    override fun getFavouriteLiveData(): LiveData<List<Train>> {
        return trainRepository.getFavouriteLiveData()
    }

    override fun removeFavourite(train: Train) {
        trainRepository.removeFavourite(train)
    }

    override fun insertFavourite(train: Train) {
        trainRepository.insertFavourite(train)
    }
}