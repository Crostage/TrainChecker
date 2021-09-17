package com.crostage.trainchecker.domain.interactors

import com.crostage.trainchecker.domain.interactors.interfaces.ISeatInteractor
import com.crostage.trainchecker.domain.network.ISeatService
import com.crostage.trainchecker.domain.model.Car
import com.crostage.trainchecker.domain.model.Train
import javax.inject.Inject

/**
 * Реализация [ISeatInteractor]
 *
 * @property ISeatService сервис для получения вагонов [Car]
 */
class SeatInteractor @Inject constructor(
    private val service: ISeatService,
) : ISeatInteractor {

    /**
     * @see ISeatInteractor.getCars
     */
    override fun getCarsRid(train: Train): Long? {
        return service.getSeatsRid(train)
    }

    /**
     * @see ISeatInteractor.getCars
     */
    override fun getCars(rid: Long): List<Car> {
        return service.getSeatsList(rid)
    }

}