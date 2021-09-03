package com.crostage.trainchecker.domain.repository

import com.crostage.trainchecker.domain.model.Station

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
    fun insertStationResponse(name: String, stationList: List<Station>)


    /**
     * Получить сохраненный отвеет от сервера
     *
     * @param name поисковый запрос, имя станции
     */
    fun getListFromName(name: String): List<Station>?

    /**
     * Сохранить станции
     *
     * @param station
     */

    fun insertStation(station: Station)

    /**
     * Получить список последних выбраных станций
     *
     * @return
     */
    fun getLastStationsPick(): List<Station>

}