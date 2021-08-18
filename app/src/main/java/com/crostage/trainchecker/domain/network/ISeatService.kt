package com.crostage.trainchecker.domain.network

import Seat
import com.crostage.trainchecker.model.data.train.Train

interface ISeatService {

    fun getSeats(train: Train): List<Seat>

}