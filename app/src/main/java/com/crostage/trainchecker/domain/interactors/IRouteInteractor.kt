package com.crostage.trainchecker.domain.interactors

import com.crostage.trainchecker.model.rout.TrainStop
import com.crostage.trainchecker.model.train.Train

interface IRouteInteractor {

    fun getRouteList(train: Train): List<TrainStop>

}