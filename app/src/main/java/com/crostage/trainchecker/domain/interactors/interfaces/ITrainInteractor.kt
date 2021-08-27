package com.crostage.trainchecker.domain.interactors.interfaces

import com.crostage.trainchecker.model.domain.Train

interface ITrainInteractor {

    fun getTrainList(codeFrom: Int, codeTo: Int, date: String): List<Train>

}
