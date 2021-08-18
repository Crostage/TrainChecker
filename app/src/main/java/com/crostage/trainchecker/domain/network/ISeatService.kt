package com.crostage.trainchecker.domain.network

import com.crostage.trainchecker.model.data.seat.Car
import com.crostage.trainchecker.model.data.train.Train

interface ISeatService {

    fun getSeats(train: Train): List<Car>

}