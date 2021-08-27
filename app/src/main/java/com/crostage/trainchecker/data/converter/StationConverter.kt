package com.crostage.trainchecker.data.converter

import com.crostage.trainchecker.model.data.station.StationEntity
import com.crostage.trainchecker.model.domain.Station
import javax.inject.Inject

class StationConverter @Inject constructor() : IConverter<StationEntity, Station> {

    override fun convert(input: StationEntity): Station {
        return Station(input.stationCode, input.stationName)
    }

    override fun revers(input: Station): StationEntity {
        return StationEntity(input.stationCode, input.stationName)
    }
}