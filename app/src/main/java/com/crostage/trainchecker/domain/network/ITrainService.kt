package com.crostage.trainchecker.domain.network

import com.crostage.trainchecker.model.data.train.TrainEntity

/**
 * Сервис для получения поездов по поисковому запросу
 *
 */

interface ITrainService {

    /**
     * Получение поездов
     *
     * @param codeFrom код города отправления
     * @param codeTo код города прибытия
     * @param date дата отправления
     * @return список поездов [TrainEntity]
     */

    fun getTrainList(codeFrom: Int, codeTo: Int, date: String): List<TrainEntity>

}