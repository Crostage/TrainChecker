package com.crostage.trainchecker.domain.network

import com.crostage.trainchecker.data.model.rout.TrainStop
import com.crostage.trainchecker.data.model.train.Train

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

    fun getRouteList(train: Train): List<TrainStop>

}