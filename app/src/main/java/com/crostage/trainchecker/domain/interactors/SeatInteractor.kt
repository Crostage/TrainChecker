package com.crostage.trainchecker.domain.interactors

import com.crostage.trainchecker.data.model.seat.Car
import com.crostage.trainchecker.data.model.train.Train
import com.crostage.trainchecker.domain.interactors.interfaces.ISeatInteractor
import com.crostage.trainchecker.domain.network.ISeatService
import javax.inject.Inject

/**
 * Реализация [ISeatInteractor]
 *
 * @property ISeatService сервис для получения вагонов и мест [Car]
 */
class SeatInteractor @Inject constructor(private val service: ISeatService) : ISeatInteractor {

    override fun getSeats(train: Train): List<Car> {
        return service.getSeats(train)
    }

}