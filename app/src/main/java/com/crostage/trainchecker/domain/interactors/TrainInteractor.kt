package com.crostage.trainchecker.domain.interactors

import com.crostage.trainchecker.data.converter.IConverter
import com.crostage.trainchecker.domain.interactors.interfaces.ITrainInteractor
import com.crostage.trainchecker.domain.network.ITrainService
import com.crostage.trainchecker.model.data.train.TrainEntity
import com.crostage.trainchecker.model.domain.Train
import javax.inject.Inject

/**
 * Реализация [ITrainInteractor]
 *
 * @property service сервис для получения [TrainEntity] данных из сети
 */
class TrainInteractor @Inject constructor(
    private val service: ITrainService,
    private val converter: IConverter<TrainEntity, Train>,
) : ITrainInteractor {

    override fun getTrainList(codeFrom: Int, codeTo: Int, date: String): List<Train> {

        return service.getTrainList(codeFrom, codeTo, date).map { converter.convert(it) }
    }


}