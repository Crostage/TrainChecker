package com.crostage.trainchecker.domain.interactors

import com.crostage.trainchecker.domain.network.ITrainService
import com.crostage.trainchecker.data.model.train.Train
import com.crostage.trainchecker.domain.interactors.interfaces.ITrainInteractor
import javax.inject.Inject

/**
 * Реализация [ITrainInteractor]
 *
 * @property service сервис для получения [Train] данных из сети
 */
class TrainInteractor @Inject constructor(private val service: ITrainService) : ITrainInteractor {

    override fun getTrainList(codeFrom: Int, codeTo: Int, date: String): List<Train> =
        service.getTrainList(codeFrom, codeTo, date)


}