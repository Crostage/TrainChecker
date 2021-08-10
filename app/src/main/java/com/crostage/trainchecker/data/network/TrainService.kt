package com.crostage.trainchecker.data.network

import com.crostage.trainchecker.data.model.routRequset.TrainStop
import com.crostage.trainchecker.data.model.trainRequest.Train

interface TrainService {

     fun getTrainList(
        codeFrom: Int,
        codeTo: Int,
        date: String
    ): List<Train>

     fun getStationCode(stationName: String): Int

     fun getTrainRoutes(train: Train): List<TrainStop>

}