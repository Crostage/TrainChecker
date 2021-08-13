package com.crostage.trainchecker.domain.network

import com.crostage.trainchecker.model.data.train.Train

interface ITrainService {

    fun getTrainList(codeFrom: Int, codeTo: Int, date: String): List<Train>

}