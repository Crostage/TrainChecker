package com.crostage.trainchecker.data.network

import com.crostage.trainchecker.data.model.routRequset.TrainStop
import com.crostage.trainchecker.data.model.trainRequest.Train

interface TrainService {

    suspend fun getTrainList(
        codeFrom: Int,
        codeTo: Int,
        date: String
    ): List<Train>

    suspend fun getStationCode(stationName: String): Int

    suspend fun getTrainRoutes(train: Train): List<TrainStop>

}