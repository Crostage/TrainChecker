package com.crostage.trainchecker.domain.interactors.interfaces

import com.crostage.trainchecker.data.model.rout.TrainStop
import com.crostage.trainchecker.data.model.train.Train

interface IRouteInteractor {

    fun getRouteList(train: Train): List<TrainStop>

}