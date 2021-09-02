package com.crostage.trainchecker.domain.interactors.interfaces

import com.crostage.trainchecker.model.data.rout.TrainStop
import com.crostage.trainchecker.model.data.train.TrainEntity
import com.crostage.trainchecker.model.domain.Train

interface IRouteInteractor {

    fun getRouteListRid(train: Train): Long?

    fun getRoutesList(rid: Long): List<TrainStop>

}