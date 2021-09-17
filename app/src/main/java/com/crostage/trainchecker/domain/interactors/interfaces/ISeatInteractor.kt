package com.crostage.trainchecker.domain.interactors.interfaces

import com.crostage.trainchecker.domain.model.Car
import com.crostage.trainchecker.domain.model.Train

/**
 * Интерактор для работы с вагоне
 *
 */
interface ISeatInteractor {

    /**
     * Получения id запроса вагонов поезда
     *
     * @param train поезд
     * @return id запросв
     */
    fun getCarsRid(train: Train): Long?

    /**
     * Получения списка вагонов поезда
     *
     * @param rid id запроса
     * @return список вагонов [Car]
     */
    fun getCars(rid: Long): List<Car>

}