package com.crostage.trainchecker.domain.interactors.interfaces

import com.crostage.trainchecker.model.data.rout.TrainStop
import com.crostage.trainchecker.model.data.train.Train

interface IRouteInteractor {

    fun getRouteList(train: Train): List<TrainStop>

}