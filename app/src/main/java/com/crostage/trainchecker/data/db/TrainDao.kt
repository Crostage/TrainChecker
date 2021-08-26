package com.crostage.trainchecker.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.crostage.trainchecker.data.model.train.Train

/**
 * Работа с данными поездов, Room
 *
 */

@Dao
interface TrainDao {

    /**
     * Получение списка поездов
     *
     * @return список поездова [Train]
     */

    @Query("SELECT*FROM trains ORDER BY dateStart DESC ")
    fun getTrainList(): LiveData<List<Train>>

    /**
     * Запись элемента в БД
     *
     * @param train
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTrain(train: Train)


    /**
     * Удаление элемента из БД
     *
     * @param train
     */

    @Delete
    fun removeTrain(train: Train)
}