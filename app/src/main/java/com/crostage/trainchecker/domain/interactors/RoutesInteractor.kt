package com.crostage.trainchecker.domain.interactors

import com.crostage.trainchecker.data.network.services.IRouteService
import com.crostage.trainchecker.model.rout.TrainStop
import com.crostage.trainchecker.model.train.Train

class RoutesInteractor(private val service: IRouteService) : IRouteService {
    override fun getRouteList(train: Train): List<TrainStop> = service.getRouteList(train)
}