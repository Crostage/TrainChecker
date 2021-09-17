package com.crostage.trainchecker.domain.interactors

import com.crostage.trainchecker.domain.interactors.interfaces.IRouteInteractor
import com.crostage.trainchecker.domain.network.IRouteService
import com.crostage.trainchecker.domain.model.Train
import com.crostage.trainchecker.domain.model.TrainStop
import javax.inject.Inject

/**
 * Реализация [IRouteInteractor]
 *
 * @property IRouteService сервис для получения маршрутов [TrainStop] из сети
 */
class RouteInteractor @Inject constructor(
    private val service: IRouteService,
) : IRouteInteractor {

    /**
     * @see IRouteInteractor.getRouteListRid
     */
    override fun getRouteListRid(train: Train): Long? {
        return service.getRouteListRequestId(train)
    }

    /**
     * @see IRouteInteractor.getRoutesList
     */
    override fun getRoutesList(rid: Long): List<TrainStop> {
        return service.getRoutesList(rid)
    }

}