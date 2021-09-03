package com.crostage.trainchecker.domain.interactors.interfaces

import com.crostage.trainchecker.domain.model.Train
import com.crostage.trainchecker.domain.model.TrainStop

interface IRouteInteractor {

    fun getRouteListRid(train: Train): Long?

    fun getRoutesList(rid: Long): List<TrainStop>

}