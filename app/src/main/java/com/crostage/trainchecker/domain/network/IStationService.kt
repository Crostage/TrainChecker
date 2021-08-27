package com.crostage.trainchecker.domain.network

import com.crostage.trainchecker.model.data.station.StationEntity

/**
 * Сервис для получения списка станций по поисковом запросу из сети
 *
 */

interface IStationService {

    /**
     * Получение списка станций
     *
     * @param stationName поисковый запрос
     * @return список станцй [StationEntity]
     */
    fun getStationList(stationName: String): List<StationEntity>

}