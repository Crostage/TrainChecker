package com.crostage.trainchecker.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.crostage.trainchecker.data.model.train.FavouriteEntity
import com.crostage.trainchecker.utils.Constant.Companion.TABLE_NAME_FAVOURITES
import io.reactivex.rxjava3.core.Observable

/**
 * Работа с данными отслеживаемых поездов, Room
 *
 */

@Dao
interface TrainDao {

    /**
     * Получение списка поездов
     *
     * @return  [LiveData] списка отслеживаемых поездов [FavouriteEntity]
     */
    @Query("SELECT*FROM $TABLE_NAME_FAVOURITES ORDER BY dateStart,timeStart ")
    fun getFavouriteLiveData(): LiveData<List<FavouriteEntity>>

    /**
     * Получение списка поездов
     *
     * @return  [Observable] списка отслеживаемых поездов [FavouriteEntity]
     */
    @Query("SELECT*FROM $TABLE_NAME_FAVOURITES ORDER BY dateStart,timeStart ")
    fun getFavouriteObservable(): Observable<List<FavouriteEntity>>

    /**
     * Запись нового отслеживаемого поезда [FavouriteEntity] в БД
     *
     * @param train отслеживаемый поезд [FavouriteEntity]
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavourite(train: FavouriteEntity)

    /**
     * Удаление отслеживаемого поезда [FavouriteEntity] из БД
     *
     * @param train отслеживаемый поезд [FavouriteEntity]
     */
    @Delete
    fun removeFavourite(train: FavouriteEntity)
}