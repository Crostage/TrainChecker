package com.crostage.trainchecker.domain.interactors

import com.crostage.trainchecker.model.train.Train

interface ITrainInteractor {

    fun getTrainList(codeFrom: Int, codeTo: Int, date: String): List<Train>

}