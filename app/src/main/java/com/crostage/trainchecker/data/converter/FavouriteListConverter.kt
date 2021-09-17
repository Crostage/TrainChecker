package com.crostage.trainchecker.data.converter

import com.crostage.trainchecker.data.model.train.FavouriteEntity
import com.crostage.trainchecker.domain.model.Train
import javax.inject.Inject

/**
 * @see IConverter конвертер списка избранных в список поездов
 */
class FavouriteListConverter @Inject constructor() :
    IConverter<@JvmSuppressWildcards List<FavouriteEntity>, @JvmSuppressWildcards List<Train>> {

    /**
     * @see IConverter.convert
     */
    override fun convert(input: List<FavouriteEntity>): List<Train> {
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
                it.isFavourite
            )
        }

    }

    /**
     * @see IConverter.revers
     */
    override fun revers(input: List<Train>): List<FavouriteEntity> {
        return input.map {
            FavouriteEntity(
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
                it.isFavourite
            )
        }
    }
}