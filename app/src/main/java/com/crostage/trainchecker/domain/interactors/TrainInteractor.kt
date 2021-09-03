package com.crostage.trainchecker.domain.interactors

import androidx.lifecycle.LiveData
import com.crostage.trainchecker.domain.interactors.interfaces.ITrainInteractor
import com.crostage.trainchecker.domain.network.ITrainService
import com.crostage.trainchecker.domain.repository.ITrainRepository
import com.crostage.trainchecker.domain.model.Train
import javax.inject.Inject

/**
 * Реализация [ITrainInteractor]
 *
 * @property service сервис для получения [Train] данных из сети
 */
class TrainInteractor @Inject constructor(
    private val service: ITrainService,
    private val trainRepository: ITrainRepository,
) : ITrainInteractor {


    override fun checkFavouritesContainsTrains(
        trains: List<Train>,
        favourite: List<Train>,
    ): List<Train> {
        return trains.map {
            it.isFavourite = favourite.contains(it)
            it
        }
    }

    override fun getTrainListRid(codeFrom: Int, codeTo: Int, date: String): Long? {
        return service.getTrainListRid(codeFrom, codeTo, date)
    }

    override fun getTrainList(rid: Long): List<Train> {
        return service.getTrainList(rid)
    }

    override fun getFavouriteLiveData(): LiveData<List<Train>> {
        return trainRepository.getFavouriteLiveData()
    }

    override fun getFavouriteList(): List<Train> {
        return trainRepository.getFavouriteList()
    }

    override fun insertTrain(train: Train) {
        trainRepository.insertTrain(train)
    }

    override fun removeTrain(train: Train) {
        trainRepository.removeTrain(train)
    }


}