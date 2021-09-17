package com.crostage.trainchecker.data.converter

import com.crostage.trainchecker.data.model.train.TrainEntity
import com.crostage.trainchecker.domain.model.Train
import javax.inject.Inject

/**
 * @see IConverter конвертер поездов
 */
class TrainConverter @Inject constructor() : IConverter<TrainEntity, Train> {

    /**
     * @see IConverter.convert
     */
    override fun convert(input: TrainEntity): Train {

        return Train(
            input.carrier,
            input.brand,
            input.ticketList,
            input.codeStationFrom,
            input.codeStationTo,
            input.nameStationFrom,
            input.nameStationTo,
            input.dateEnd,
            input.dateStart,
            input.trainNumber,
            input.timeStart,
            input.timeEnd,
            input.timeInWay,
            false
        )
    }


    /**
     * @see IConverter.revers
     */
    override fun revers(input: Train): TrainEntity {

        return TrainEntity(
            input.carrier,
            input.brand,
            input.ticketList,
            input.codeStationFrom,
            input.codeStationTo,
            input.nameStationFrom,
            input.nameStationTo,
            input.dateEnd,
            input.dateStart,
            input.trainNumber,
            input.timeStart,
            input.timeEnd,
            input.timeInWay
        )
    }
}