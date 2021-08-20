package com.crostage.trainchecker.data.repository

import androidx.lifecycle.LiveData
import androidx.room.*
import com.crostage.trainchecker.model.data.train.Train

@Dao
interface TrainDao {

    @Query("SELECT*FROM trains ORDER BY dateStart DESC ")
    fun getTrainList(): LiveData<List<Train>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTrain(train: Train)

    @Delete
    fun removeTrain(train: Train)
}