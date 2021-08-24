package com.crostage.trainchecker.domain.interactors

import com.crostage.trainchecker.domain.interactors.interfaces.IRouteInteractor
import com.crostage.trainchecker.domain.network.IRouteService
import com.crostage.trainchecker.data.model.rout.TrainStop
import com.crostage.trainchecker.data.model.train.Train
import com.crostage.trainchecker.domain.interactors.interfaces.IFavouriteInteractor
import javax.inject.Inject

/**
 * Реализация [IRouteInteractor]
 *
 * @property IRouteService сервис для получения маршрутов [TrainStop] из сети
 */
class RouteInteractor @Inject constructor(private val service: IRouteService) : IRouteInteractor {

    override fun getRouteList(train: Train): List<TrainStop> = service.getRouteList(train)

}