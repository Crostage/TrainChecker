package com.crostage.trainchecker.domain.interactors.interfaces

import com.crostage.trainchecker.domain.model.Station
import io.reactivex.rxjava3.core.Single

/**
 * Интерактор для работы со станциями
 *
 */
interface IStationInteractor {

    /**
     * Получения списока станций по поисковому запросу из БД
     *
     * @param name поисковый запрос
     * @return список [Station]
     */
    fun getStationListFromRepo(name: String): List<Station>

    /**
     * Получения списока станций по поисковому запросу из сети
     *
     * @param name поисковый запрос
     * @return список [Station]
     */
    fun getStationListFromService(name: String): Single<List<Station>>

    /**
     * Добавление станции в таблицу последних выбраных
     *
     * @param station станция
     */
    fun insertStation(station: Station)

    /**
     * Получение списка последних выбранных стацний
     *
     * @return список [Station]
     */
    fun getLastStationsPick(): List<Station>


}