package com.crostage.trainchecker.domain.interactors.interfaces

import androidx.lifecycle.LiveData
import com.crostage.trainchecker.domain.model.Train

/**
 * Интерактор для работы с избранными поездвам
 *
 */

interface IFavouriteInteractor {

    /**
     * Получение списка избранных поездов
     *
     * @return наблюдаемый список [Train]
     */
    fun getFavouriteLiveData(): LiveData<List<Train>>

    /**
     * Удаление поезда из избранного
     *
     * @param train
     */
    fun removeFavourite(train: Train)

    /**
     * Сохарнение поезда в избранное
     *
     * @param train
     */
    fun insertFavourite(train: Train)
}