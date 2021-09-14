package com.crostage.trainchecker.data.converter

import com.crostage.trainchecker.data.model.train.TrainEntity
import com.crostage.trainchecker.domain.converter.IConverter
import com.crostage.trainchecker.domain.model.Train
import javax.inject.Inject

class TrainListConverter @Inject constructor() :
    IConverter<@JvmSuppressWildcards List<TrainEntity>, @JvmSuppressWildcards List<Train>> {
    override fun convert(input: List<TrainEntity>): List<Train> {
        return input.map {
            Train(
                it.carrier,
                it.brand,
                it.ticketList,
                it.codeStationFrom,
                it.codeStationTo,
                it.nameStationFrom,
                it.nameStationTo,
                it.dateEnd,
                it.dateStart,
                it.trainNumber,
                it.timeStart,
                it.timeEnd,
                it.timeInWay,
                false
            )
        }

    }

    override fun revers(input: List<Train>): List<TrainEntity> {
        return input.map {
            TrainEntity(
                it.carrier,
                it.brand,
                it.ticketList,
                it.codeStationFrom,
                it.codeStationTo,
                it.nameStationFrom,
                it.nameStationTo,
                it.dateEnd,
                it.dateStart,
                it.trainNumber,
                it.timeStart,
                it.timeEnd,
                it.timeInWay,
            )
        }
    }
}