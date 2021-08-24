package com.crostage.trainchecker.domain.network

import com.crostage.trainchecker.data.model.seat.Car
import com.crostage.trainchecker.data.model.train.Train

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

    fun getSeats(train: Train): List<Car>

}