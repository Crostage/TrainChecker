package com.crostage.trainchecker.data.converter

import com.crostage.trainchecker.data.model.station.StationEntity
import com.crostage.trainchecker.domain.model.Station
import javax.inject.Inject

/**
 * @see IConverter конвертер станций
 */
class StationConverter @Inject constructor() : IConverter<StationEntity, Station> {

    /**
     * @see IConverter.convert
     */
    override fun convert(input: StationEntity): Station {
        return Station(input.stationCode, input.stationName)
    }

    /**
     * @see IConverter.revers
     */
    override fun revers(input: Station): StationEntity {
        return StationEntity(input.stationCode, input.stationName)
    }
}