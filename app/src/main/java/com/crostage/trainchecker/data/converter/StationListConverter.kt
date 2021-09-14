package com.crostage.trainchecker.data.converter

import com.crostage.trainchecker.data.model.station.StationEntity
import com.crostage.trainchecker.domain.converter.IConverter
import com.crostage.trainchecker.domain.model.Station
import javax.inject.Inject

class StationListConverter @Inject constructor() :
    IConverter<@JvmSuppressWildcards List<StationEntity>, @JvmSuppressWildcards List<Station>> {

    override fun convert(input: List<StationEntity>): List<Station> {
        return input.map {
            Station(
                it.stationCode,
                it.stationName
            )
        }
    }

    override fun revers(input: List<Station>): List<StationEntity> {
        return input.map {
            StationEntity(
                it.stationCode,
                it.stationName
            )
        }
    }


}