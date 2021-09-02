package com.crostage.trainchecker.domain.interactors

import com.crostage.trainchecker.data.converter.IConverter
import com.crostage.trainchecker.domain.interactors.interfaces.IRouteInteractor
import com.crostage.trainchecker.domain.network.IRouteService
import com.crostage.trainchecker.model.data.rout.TrainStop
import com.crostage.trainchecker.model.data.train.TrainEntity
import com.crostage.trainchecker.model.domain.Train
import javax.inject.Inject

/**
 * Реализация [IRouteInteractor]
 *
 * @property IRouteService сервис для получения маршрутов [TrainStop] из сети
 */
class RouteInteractor @Inject constructor(
    private val service: IRouteService,
    private val converter: IConverter<TrainEntity, Train>,
) : IRouteInteractor {

    override fun getRouteListRid(train: Train): Long? {
        return service.getRouteListRequestId(converter.revers(train))
    }

    override fun getRoutesList(rid: Long): List<TrainStop> {
        return service.getResultFormRoutesId(rid)
    }

}