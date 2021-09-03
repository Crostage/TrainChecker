package com.crostage.trainchecker.domain.network

import com.crostage.trainchecker.data.model.seat.CarDto
import com.crostage.trainchecker.domain.model.Car
import com.crostage.trainchecker.domain.model.Train

/**
 * Сервис для получения данных о свободных местах из сети
 *
 */

interface ISeatService {

    /**
     * Получение списка вагонов со свободными местами
     *
     * @param train поезд
     * @return список вагонов [CarDto]
     */

    fun getSeatsRid(train: Train): Long?

    fun getSeatsList(rid: Long): List<Car>

}