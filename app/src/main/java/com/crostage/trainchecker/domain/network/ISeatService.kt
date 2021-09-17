package com.crostage.trainchecker.domain.network

import com.crostage.trainchecker.domain.model.Car
import com.crostage.trainchecker.domain.model.Train

/**
 * Сервис для получения данных о свободных местах из сети
 *
 */
interface ISeatService {

    /**
     * Получение ID для свободных мест
     *
     * @param train наблюдаемый поезд
     * @return request id
     */
    fun getSeatsRid(train: Train): Long?

    /**
     * Получение списка вагонов со свободными местами
     *
     * @param rid id запросв
     * @return список вагонов [Car]
     */
    fun getSeatsList(rid: Long): List<Car>

}