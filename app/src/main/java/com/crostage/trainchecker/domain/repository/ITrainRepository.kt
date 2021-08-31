package com.crostage.trainchecker.domain.repository

import androidx.lifecycle.LiveData
import com.crostage.trainchecker.model.data.train.TrainEntity
import com.crostage.trainchecker.model.domain.Train


/**
 * Репозиторий поездов
 *
 */

interface ITrainRepository {

    /**
     * Получение поездов
     *
     * @return список [TrainEntity]
     */
    fun getFavouriteLiveData(): LiveData<List<Train>>


    fun getFavouriteList(): List<Train>

    /**
     * Сохранение поезда
     *
     * @param train
     */
    fun insertTrain(train: Train)

    /**
     * Удаление поезда
     *
     * @param train
     */
    fun removeTrain(train: Train)
}