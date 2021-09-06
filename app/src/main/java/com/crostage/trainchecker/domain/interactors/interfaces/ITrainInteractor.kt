package com.crostage.trainchecker.domain.interactors.interfaces

import androidx.lifecycle.LiveData
import com.crostage.trainchecker.domain.model.Train

interface ITrainInteractor {

    /**
     * Получение request id поискового запроса
     *
     * @param codeFrom код станции отправления
     * @param codeTo код станции прибытия
     * @param date дата отправления
     * @return request id
     */

    fun getTrainListRid(codeFrom: Int, codeTo: Int, date: String): Long?

    /**
     * Получение по [rid] списка поездов соответствующих поисковому запросу
     *
     * @return наблюдаемый список [Train]
     */

    fun getTrainList(rid: Long): List<Train>


    fun checkFavouritesContainsTrains(trains: List<Train>, favourite: List<Train>): List<Train>



}
