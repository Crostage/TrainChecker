package com.crostage.trainchecker.domain.interactors.interfaces

import androidx.lifecycle.LiveData
import com.crostage.trainchecker.model.domain.Train

interface ITrainInteractor {

    /**
     * Получение списка поездов соответствующих поисковому запросу
     *
     * @return наблюдаемый список [Train]
     */

    fun getTrainList(codeFrom: Int, codeTo: Int, date: String): List<Train>

    /**
     * Получение списка избранных поездов
     *
     * @return наблюдаемый список [Train]
     */
    fun getFavouriteLiveData(): LiveData<List<Train>>


    fun checkFavouritesContainsTrains(trains: List<Train>, favourite: List<Train>): List<Train>

    fun getFavouriteList(): List<Train>

    /**
     * Сохарнение поезда в избранное
     *
     * @param train
     */


    fun insertTrain(train: Train)


    /**
     * Удаление поезда из избранного
     *
     * @param train
     */
    fun removeTrain(train: Train)

}
