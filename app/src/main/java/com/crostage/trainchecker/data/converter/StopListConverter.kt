package com.crostage.trainchecker.data.converter

import com.crostage.trainchecker.data.model.rout.TrainStopDto
import com.crostage.trainchecker.domain.model.TrainStop
import javax.inject.Inject

/**
 * @see IConverter конвертер остановк
 */
class StopListConverter @Inject constructor() :
    IConverter<@JvmSuppressWildcards List<TrainStopDto>, @JvmSuppressWildcards List<TrainStop>> {

    /**
     * @see IConverter.convert
     */
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

    /**
     * @see IConverter.revers
     */
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