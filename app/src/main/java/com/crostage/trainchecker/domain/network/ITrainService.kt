package com.crostage.trainchecker.domain.network

import com.crostage.trainchecker.domain.model.Train

/**
 * Сервис для получения поездов по поисковому запросу
 *
 */
interface ITrainService {

    /**
     * Получение id для поездов
     *
     * @param codeFrom код города отправления
     * @param codeTo код города прибытия
     * @param date дата отправления
     * @return id запроса
     */
    fun getTrainListRid(codeFrom: Int, codeTo: Int, date: String): Long?

    /**
     * Получение списка поездов
     *
     * @param rid id запроса
     * @return список поездов [Train]
     */
    fun getTrainList(rid: Long?): List<Train>

}