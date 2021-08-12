package com.crostage.trainchecker.domain.interactors

import com.crostage.trainchecker.data.network.services.IStationService
import com.crostage.trainchecker.model.station.Station

class StationInteractor(private val service: IStationService) : IStationInteractor {

    override fun getStationList(name: String): List<Station> = service.getStationList(name)


}