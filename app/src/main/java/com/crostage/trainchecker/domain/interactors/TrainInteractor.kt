package com.crostage.trainchecker.domain.interactors

import com.crostage.trainchecker.domain.network.ITrainService
import com.crostage.trainchecker.model.train.Train
import com.crostage.trainchecker.domain.interactors.interfaces.ITrainInteractor

class TrainInteractor(private val service: ITrainService) : ITrainInteractor {

    override fun getTrainList(codeFrom: Int, codeTo: Int, date: String): List<Train> =
        service.getTrainList(codeFrom, codeTo, date)


}