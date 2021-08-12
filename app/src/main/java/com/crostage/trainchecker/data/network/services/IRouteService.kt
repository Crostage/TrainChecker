package com.crostage.trainchecker.data.network.services

import com.crostage.trainchecker.model.rout.TrainStop
import com.crostage.trainchecker.model.train.Train

interface IRouteService {

    fun getRouteList(train: Train): List<TrainStop>

}