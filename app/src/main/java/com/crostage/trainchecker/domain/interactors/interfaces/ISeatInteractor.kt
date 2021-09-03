package com.crostage.trainchecker.domain.interactors.interfaces

import com.crostage.trainchecker.domain.model.Car
import com.crostage.trainchecker.domain.model.Train

interface ISeatInteractor {

    fun getSeatsRid(train: Train): Long?

    fun getSeats(rid: Long): List<Car>

}