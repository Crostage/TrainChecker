package com.crostage.trainchecker.domain.interactors.interfaces

import com.crostage.trainchecker.data.model.train.Train

interface ITrainInteractor {

    fun getTrainList(codeFrom: Int, codeTo: Int, date: String): List<Train>

}