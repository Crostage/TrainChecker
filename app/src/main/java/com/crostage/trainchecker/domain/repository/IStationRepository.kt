package com.crostage.trainchecker.domain.repository

import com.crostage.trainchecker.data.model.station.StationSearchResponse

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

}