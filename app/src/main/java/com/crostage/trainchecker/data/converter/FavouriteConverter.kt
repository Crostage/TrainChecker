package com.crostage.trainchecker.data.converter

import com.crostage.trainchecker.data.model.train.FavouriteEntity
import com.crostage.trainchecker.domain.model.Train
import javax.inject.Inject

class FavouriteConverter @Inject constructor() : IConverter<FavouriteEntity, Train> {
    override fun convert(input: FavouriteEntity): Train {

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
            input.isFavourite
        )
    }

    override fun revers(input: Train): FavouriteEntity {

        return FavouriteEntity(
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
            input.isFavourite
        )
    }
}