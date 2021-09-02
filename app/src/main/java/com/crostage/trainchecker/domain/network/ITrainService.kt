package com.crostage.trainchecker.domain.network

import com.crostage.trainchecker.model.domain.Train

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
     * @return список поездов [Train]
     */

    fun getTrainListRid(codeFrom: Int, codeTo: Int, date: String): Long?

    fun getTrainList(rid: Long): List<Train>

}