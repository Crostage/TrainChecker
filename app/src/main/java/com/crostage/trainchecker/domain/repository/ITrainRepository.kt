package com.crostage.trainchecker.domain.repository

import androidx.lifecycle.LiveData
import com.crostage.trainchecker.model.data.train.TrainEntity


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
    fun getTrainList(): LiveData<List<TrainEntity>>

    /**
     * Сохранение поезда
     *
     * @param train
     */
    fun insertTrain(train: TrainEntity)

    /**
     * Удаление поезда
     *
     * @param train
     */
    fun removeTrain(train: TrainEntity)
}