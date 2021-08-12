package com.crostage.trainchecker.domain.interactors

import com.crostage.trainchecker.data.network.services.ITrainService
import com.crostage.trainchecker.model.train.Train

class TrainInteractor(val service:ITrainService) : ITrainInteractor{

    override fun getTrainList(codeFrom: Int, codeTo: Int, date: String): List<Train> =
        service.getTrainList(codeFrom,codeTo,date)


}