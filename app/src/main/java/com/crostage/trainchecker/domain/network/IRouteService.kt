package com.crostage.trainchecker.domain.network

import com.crostage.trainchecker.domain.model.Train
import com.crostage.trainchecker.domain.model.TrainStop

/**
 * Сервис для получения данных о маршрутах из сети
 *
 */
interface IRouteService {

    /**
     * Получение ID для маршрутов
     *
     * @param train наблюдаемый поезд
     * @return request id
     */
    fun getRouteListRequestId(train: Train): Long?

    /**
     * Получение маршрута конкретного поезда
     *
     * @param rid  ID запроса
     * @return список остановок [TrainStop]
     */
    fun getRoutesList(rid: Long): List<TrainStop>

}