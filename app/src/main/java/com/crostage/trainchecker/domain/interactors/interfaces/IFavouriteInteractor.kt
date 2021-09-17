package com.crostage.trainchecker.domain.interactors.interfaces

import androidx.lifecycle.LiveData
import com.crostage.trainchecker.domain.model.Train

/**
 * Интерактор для работы с отслеживаемыми поездами
 *
 */
interface IFavouriteInteractor {

    /**
     * Получение списка отслеживаемых поездов
     *
     * @return наблюдаемый список [Train]
     */
    fun getFavouriteLiveData(): LiveData<List<Train>>

    /**
     * Удаление поезда из отслеживания
     *
     * @param train поезд
     */
    fun removeFavourite(train: Train)

    /**
     * Отслеживание поезда
     *
     * @param train поезд
     */
    fun insertFavourite(train: Train)
}