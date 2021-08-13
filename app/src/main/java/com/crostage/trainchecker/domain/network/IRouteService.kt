package com.crostage.trainchecker.domain.network

import com.crostage.trainchecker.model.data.rout.TrainStop
import com.crostage.trainchecker.model.data.train.Train

interface IRouteService {

    fun getRouteList(train: Train): List<TrainStop>

}