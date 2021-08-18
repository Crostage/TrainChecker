package com.crostage.trainchecker.domain.interactors

import com.crostage.trainchecker.domain.interactors.interfaces.ISeatInteractor
import com.crostage.trainchecker.domain.network.ISeatService
import com.crostage.trainchecker.model.data.seat.Car
import com.crostage.trainchecker.model.data.train.Train
import javax.inject.Inject

class SeatInteractor @Inject constructor(private val service: ISeatService) : ISeatInteractor {

    override fun getSeats(train: Train): List<Car> {
        return service.getSeats(train)
    }

}