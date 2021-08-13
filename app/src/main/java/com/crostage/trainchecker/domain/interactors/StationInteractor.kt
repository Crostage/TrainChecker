package com.crostage.trainchecker.domain.interactors

import com.crostage.trainchecker.domain.network.IStationService
import com.crostage.trainchecker.model.station.Station
import com.crostage.trainchecker.domain.interactors.interfaces.IStationInteractor

class StationInteractor(private val service: IStationService) : IStationInteractor {

    override fun getStationList(name: String): List<Station> = service.getStationList(name)


}