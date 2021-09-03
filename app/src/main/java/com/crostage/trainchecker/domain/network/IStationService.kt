package com.crostage.trainchecker.domain.network

import com.crostage.trainchecker.domain.model.Station

/**
 * Сервис для получения списка станций по поисковом запросу из сети
 *
 */

interface IStationService {

    /**
     * Получение списка станций
     *
     * @param stationName поисковый запрос
     * @return список станцй [Station]
     */
    fun getStationList(stationName: String): List<Station>?

}