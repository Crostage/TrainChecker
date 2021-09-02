package com.crostage.trainchecker.domain.interactors.interfaces

import com.crostage.trainchecker.model.data.seat.Car
import com.crostage.trainchecker.model.domain.Train

interface ISeatInteractor {

    fun getSeatsRid(train: Train): Long?

    fun getSeats(rid: Long): List<Car>

}