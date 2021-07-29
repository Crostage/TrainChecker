package com.crostage.trainchecker.repository

import androidx.lifecycle.LiveData
import androidx.room.*
import com.crostage.trainchecker.model.stationRequest.Station
import com.crostage.trainchecker.model.trainRequest.Train


@Dao
interface TrainDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStation(station: Station)

    @Query("SELECT*FROM stations ORDER BY n DESC ")
    suspend fun getStationList(): List<Station>

    @Query("SELECT*FROM trains ORDER BY id DESC ")
    fun getTrainList(): LiveData<List<Train>>

}