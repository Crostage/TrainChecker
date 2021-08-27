package com.crostage.trainchecker.domain.network

import com.crostage.trainchecker.model.data.rout.TrainStop
import com.crostage.trainchecker.model.data.train.TrainEntity

/**
 * Сервис для получения данных о маршрутах из сети
 *
 */

interface IRouteService {

    /**
     * Получение списка маршрутов для определенного поезда
     *
     * @param train поезд
     * @return список остановок [TrainStop]
     */

    fun getRouteList(train: TrainEntity): List<TrainStop>

}