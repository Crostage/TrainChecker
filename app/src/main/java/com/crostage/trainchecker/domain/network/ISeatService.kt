package com.crostage.trainchecker.domain.network

import com.crostage.trainchecker.model.data.seat.Car
import com.crostage.trainchecker.model.data.train.TrainEntity
import com.crostage.trainchecker.model.domain.Train

/**
 * Сервис для получения данных о свободных местах из сети
 *
 */

interface ISeatService {

    /**
     * Получение списка вагонов со свободными местами
     *
     * @param train поезд
     * @return список вагонов [Car]
     */

    fun getSeatsRid(train: Train): Long?

    fun getSeatsList(rid: Long): List<Car>

}