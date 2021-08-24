package com.crostage.trainchecker.domain.interactors.interfaces

import androidx.lifecycle.LiveData
import com.crostage.trainchecker.data.model.train.Train

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
    fun getTrainList(): LiveData<List<Train>>

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