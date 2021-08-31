package com.crostage.trainchecker.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.crostage.trainchecker.model.data.train.TrainEntity

/**
 * Работа с данными поездов, Room
 *
 */

@Dao
interface TrainDao {

    /**
     * Получение списка поездов
     *
     * @return список поездова [TrainEntity]
     */

    @Query("SELECT*FROM trains ORDER BY dateStart DESC ")
    fun getFavouriteLiveData(): LiveData<List<TrainEntity>>

    @Query("SELECT*FROM trains ORDER BY dateStart DESC ")
    fun getFavouriteList(): List<TrainEntity>

    /**
     * Запись элемента в БД
     *
     * @param train
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTrain(train: TrainEntity)

    /**
     * Удаление элемента из БД
     *
     * @param train
     */

    @Delete
    fun removeTrain(train: TrainEntity)
}