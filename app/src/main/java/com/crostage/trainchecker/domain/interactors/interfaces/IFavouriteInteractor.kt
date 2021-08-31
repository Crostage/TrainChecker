package com.crostage.trainchecker.domain.interactors.interfaces

import androidx.lifecycle.LiveData
import com.crostage.trainchecker.model.data.train.TrainEntity
import com.crostage.trainchecker.model.domain.Train

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
    fun getFavouriteTrainList(): LiveData<List<Train>>


    /**
     * Удаление поезда из избранного
     *
     * @param train
     */
    fun removeTrain(train: Train)
}