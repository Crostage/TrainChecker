package com.crostage.trainchecker.data.network

import com.crostage.trainchecker.data.model.routRequset.TrainStop
import com.crostage.trainchecker.data.model.stationRequest.Station
import com.crostage.trainchecker.data.model.trainRequest.Train

interface ITrainService {

     fun getTrainList(
        codeFrom: Int,
        codeTo: Int,
        date: String
    ): List<Train>

     fun getStationCode(stationName: String): List<Station>?

     fun getTrainRoutes(train: Train): List<TrainStop>

}