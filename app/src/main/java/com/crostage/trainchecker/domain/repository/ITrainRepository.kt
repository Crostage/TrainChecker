package com.crostage.trainchecker.domain.repository

import androidx.lifecycle.LiveData
import com.crostage.trainchecker.data.model.train.Train


/**
 * Репозиторий поездов
 *
 */

interface ITrainRepository {

    /**
     * Получение поездов
     *
     * @return список [Train]
     */
    fun getTrainList(): LiveData<List<Train>>

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