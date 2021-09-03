package com.crostage.trainchecker.data.converter

import com.crostage.trainchecker.data.model.rout.TrainStopDto
import com.crostage.trainchecker.domain.model.TrainStop
import javax.inject.Inject

class TrainStopListConverter @Inject constructor() :
    IConverter<@JvmSuppressWildcards List<TrainStopDto>, @JvmSuppressWildcards List<TrainStop>> {

    override fun convert(input: List<TrainStopDto>): List<TrainStop> {
        return input.map {
            TrainStop(
                it.code,
                it.arriveTime ?: "",
                it.distance,
                it.stationName
            )
        }
    }

    override fun revers(input: List<TrainStop>): List<TrainStopDto> {
        return input.map {
            TrainStopDto(
                it.code,
                it.arriveTime,
                it.distance,
                it.stationName
            )
        }
    }


}