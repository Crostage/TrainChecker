package com.crostage.trainchecker.domain.interactors

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.crostage.trainchecker.data.converter.IConverter
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
    private val converter: IConverter<TrainEntity, Train>,
) : IFavouriteInteractor {

    override fun getTrainList(): LiveData<List<Train>> {

        val trainEntityLiveData: LiveData<List<TrainEntity>> = trainRepository.getTrainList()

        return Transformations.map(trainEntityLiveData) { list ->
            list.map { converter.convert(it) }
        }
    }

    override fun insertTrain(train: Train) {
        val trainEntity: TrainEntity = converter.revers(train)
        trainRepository.insertTrain(trainEntity)
    }

    override fun removeTrain(train: Train) {
        trainRepository.removeTrain(converter.revers(train))
    }
}