package com.crostage.trainchecker.domain.repository

import com.crostage.trainchecker.domain.model.Station

/**
 * Репозиторий станций
 *
 */
interface IStationRepository {


    /**
     * Сохранение списка станций по поисковому запросу
     *
     * @param name поисковый запрос
     * @param stationList список станций [Station]
     */
    fun insertStationResponse(name: String, stationList: List<Station>)


    /**
     * Получение из кэша список поездов по поисковому запросу
     *
     * @param name поисковый запрос, имя станции
     */
    fun getListFromName(name: String): List<Station>?

    /**
     * Сохранить последнюю выбранную стацнию
     *
     * @param station стацния
     */
    fun insertStation(station: Station)

    /**
     * Получить список последних выбраных станций
     *
     * @return список станций [Station]
     */
    fun getLastStationsPick(): List<Station>

}