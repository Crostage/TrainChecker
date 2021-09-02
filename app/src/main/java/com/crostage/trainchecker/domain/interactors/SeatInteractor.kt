package com.crostage.trainchecker.domain.interactors

import com.crostage.trainchecker.data.converter.IConverter
import com.crostage.trainchecker.domain.interactors.interfaces.ISeatInteractor
import com.crostage.trainchecker.domain.network.ISeatService
import com.crostage.trainchecker.model.data.seat.Car
import com.crostage.trainchecker.model.data.train.TrainEntity
import com.crostage.trainchecker.model.domain.Train
import javax.inject.Inject

/**
 * Реализация [ISeatInteractor]
 *
 * @property ISeatService сервис для получения вагонов и мест [Car]
 */
class SeatInteractor @Inject constructor(
    private val service: ISeatService,
) : ISeatInteractor {


    override fun getSeatsRid(train: Train): Long? {
        return service.getSeatsRid(train)
    }

    override fun getSeats(rid: Long): List<Car> {
        return service.getSeatsList(rid)
    }

}