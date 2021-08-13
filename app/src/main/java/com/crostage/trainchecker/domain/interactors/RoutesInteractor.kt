package com.crostage.trainchecker.domain.interactors

import com.crostage.trainchecker.domain.interactors.interfaces.IRouteInteractor
import com.crostage.trainchecker.domain.network.IRouteService
import com.crostage.trainchecker.model.data.rout.TrainStop
import com.crostage.trainchecker.model.data.train.Train

class RoutesInteractor(private val service: IRouteService) : IRouteInteractor {

    override fun getRouteList(train: Train): List<TrainStop> = service.getRouteList(train)

}