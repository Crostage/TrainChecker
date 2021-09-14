package com.crostage.trainchecker.data.converter

import com.crostage.trainchecker.data.model.station.StationEntity
import com.crostage.trainchecker.domain.converter.IConverter
import com.crostage.trainchecker.domain.model.Station
import javax.inject.Inject

class StationConverter @Inject constructor() : IConverter<StationEntity, Station> {

    override fun convert(input: StationEntity): Station {
        return Station(input.stationCode, input.stationName)
    }

    override fun revers(input: Station): StationEntity {
        return StationEntity(input.stationCode, input.stationName)
    }
}