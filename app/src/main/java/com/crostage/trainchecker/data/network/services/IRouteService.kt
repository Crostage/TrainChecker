package com.crostage.trainchecker.data.network.services

import com.crostage.trainchecker.model.rout.TrainStop
import com.crostage.trainchecker.model.train.Train

interface IRouteService {

    fun getTrainRoutes(train: Train): List<TrainStop>

}