package com.crostage.trainchecker.domain.repository

import com.crostage.trainchecker.model.data.station.StationEntity
import com.crostage.trainchecker.model.data.station.StationSearchResponse

/**
 * Репозиторий станций
 *
 */

interface IStationRepository {


    /**
     * Сохранить полученный ответ от сервера
     *
     * @param response ответ от сервера
     */
    fun insertStationResponse(response: StationSearchResponse)


    /**
     * Получить сохраненный отвеет от сервера
     *
     * @param name поисковый запрос, имя станции
     */
    fun getListFromName(name: String): StationSearchResponse?

    /**
     * Сохранить станции
     *
     * @param station
     */

    fun insertStation(station: StationEntity)

    /**
     * Получить список последних выбраных станций
     *
     * @return
     */
    fun getLastStationsPick(): List<StationEntity>

}