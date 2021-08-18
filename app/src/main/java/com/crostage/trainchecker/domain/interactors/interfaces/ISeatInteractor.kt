package com.crostage.trainchecker.domain.interactors.interfaces

import com.crostage.trainchecker.model.data.seat.Car
import com.crostage.trainchecker.model.data.train.Train

interface ISeatInteractor {

    fun getSeats(train: Train): List<Car>

}