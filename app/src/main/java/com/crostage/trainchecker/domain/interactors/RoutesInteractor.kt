package com.crostage.trainchecker.domain.interactors

import com.crostage.trainchecker.data.network.services.IRouteInteractor
import com.crostage.trainchecker.model.rout.TrainStop
import com.crostage.trainchecker.model.train.Train

class RoutesInteractor(val interactor: IRouteInteractor) : IRouteInteractor {
    override fun getRouteList(train: Train): List<TrainStop> = interactor.getRouteList(train)
}