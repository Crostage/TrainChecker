package com.crostage.trainchecker.domain.repository

import androidx.lifecycle.LiveData
import com.crostage.trainchecker.data.model.train.TrainEntity
import com.crostage.trainchecker.domain.model.Train
import io.reactivex.rxjava3.core.Observable


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

    fun getFavouriteObservable(): Observable<List<Train>>

    /**
     * Сохранение поезда
     *
     * @param train
     */
    fun insertFavourite(train: Train)

    /**
     * Удаление поезда
     *
     * @param train
     */
    fun removeFavourite(train: Train)
}