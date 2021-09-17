package com.crostage.trainchecker.domain.interactors.interfaces

import com.crostage.trainchecker.domain.model.Train
import com.crostage.trainchecker.domain.model.TrainStop

/**
 * Интерактор для работы с маршуратми
 *
 */
interface IRouteInteractor {

    /**
     * Получения id запроса маршрутов
     *
     * @param train поезда
     * @return id запроса
     */
    fun getRouteListRid(train: Train): Long?

    /**
     * Получения списка остановк по id
     *
     * @param rid id запроса
     * @return список [TrainStop]
     */
    fun getRoutesList(rid: Long): List<TrainStop>

}