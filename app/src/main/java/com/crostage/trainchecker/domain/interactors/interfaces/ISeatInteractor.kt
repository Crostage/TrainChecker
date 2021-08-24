package com.crostage.trainchecker.domain.interactors.interfaces

import com.crostage.trainchecker.data.model.seat.Car
import com.crostage.trainchecker.data.model.train.Train

interface ISeatInteractor {

    fun getSeats(train: Train): List<Car>

}