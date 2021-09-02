package com.crostage.trainchecker.domain.network

import com.crostage.trainchecker.model.data.rout.TrainStop
import com.crostage.trainchecker.model.data.train.TrainEntity

/**
 * Сервис для получения данных о маршрутах из сети
 *
 */

interface IRouteService {

    /**
     * Запрос для получения ID для  маршрутов
     *
     * @param train наблюдаемый поезд
     * @return список остановок [TrainStop]
     */

    fun getRouteListRequestId(train: TrainEntity): Long?


    /**
     * Запрос для получения маршрута конкретного поезда
     *
     * @param rid  ID запроса
     * @return список остановок [TrainStop]
     */

    fun getResultFormRoutesId(rid: Long): List<TrainStop>

}