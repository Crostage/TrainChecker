package com.crostage.trainchecker.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.crostage.trainchecker.data.model.train.FavouriteEntity
import com.crostage.trainchecker.utils.Constant.Companion.TABLE_NAME_FAVOURITES

/**
 * Работа с данными поездов, Room
 *
 */

@Dao
interface TrainDao {

    /**
     * Получение списка поездов
     *
     * @return список поездова [FavouriteEntity]
     */

    @Query("SELECT*FROM $TABLE_NAME_FAVOURITES ORDER BY dateStart,timeStart ")
    fun getFavouriteLiveData(): LiveData<List<FavouriteEntity>>

    @Query("SELECT*FROM $TABLE_NAME_FAVOURITES ORDER BY dateStart ")
    fun getFavouriteList(): List<FavouriteEntity>

    /**
     * Запись элемента в БД
     *
     * @param train
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavourite(train: FavouriteEntity)

    /**
     * Удаление элемента из БД
     *
     * @param train
     */

    @Delete
    fun removeFavourite(train: FavouriteEntity)
}