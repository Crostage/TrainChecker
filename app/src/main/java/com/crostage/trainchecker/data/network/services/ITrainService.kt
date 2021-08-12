package com.crostage.trainchecker.data.network.services

import com.crostage.trainchecker.model.train.Train

interface ITrainService {

    fun getTrainList(codeFrom: Int, codeTo: Int, date: String): List<Train>

}