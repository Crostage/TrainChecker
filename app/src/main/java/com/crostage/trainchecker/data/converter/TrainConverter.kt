package com.crostage.trainchecker.data.converter

import com.crostage.trainchecker.model.data.train.TrainEntity
import com.crostage.trainchecker.model.domain.Train
import javax.inject.Inject

class TrainConverter @Inject constructor() : IConverter<TrainEntity, Train> {
    override fun convert(input: TrainEntity): Train {

        return Train(
            input.carrier,
            input.brand,
            TicketConverter().convert(input.ticketList),
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

    override fun revers(input: Train): TrainEntity {

        return TrainEntity(
            input.carrier,
            input.brand,
            TicketConverter().revers(input.ticketList),
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