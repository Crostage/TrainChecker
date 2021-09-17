package com.crostage.trainchecker.domain.repository

import androidx.lifecycle.LiveData
import com.crostage.trainchecker.domain.model.Train
import io.reactivex.rxjava3.core.Observable


/**
 * Репозиторий поездов
 *
 */

interface ITrainRepository {

    /**
     * Получение отслеживаемых поездов
     *
     * @return список [Train]
     */
    fun getFavouriteLiveData(): LiveData<List<Train>>

    /**
     * Получение отслеживаемых поездов
     *
     * @return список [Train]
     */
    fun getFavouriteObservable(): Observable<List<Train>>

    /**
     * Сохранение поезда в отслеживаемые
     *
     * @param train поезд
     */
    fun insertFavourite(train: Train)

    /**
     * Удаление поезда из отслеживаемых
     *
     * @param train поезд
     */
    fun removeFavourite(train: Train)
}