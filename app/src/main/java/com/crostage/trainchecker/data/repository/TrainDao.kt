package com.crostage.trainchecker.data.repository

import androidx.lifecycle.LiveData
import androidx.room.*
import com.crostage.trainchecker.data.model.stationRequest.Station
import com.crostage.trainchecker.data.model.trainRequest.Train


@Dao
interface TrainDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStation(station: Station)

    @Query("SELECT*FROM stations ORDER BY stationName DESC ")
    suspend fun getStationList(): List<Station>

//    @Query("SELECT*FROM trains ORDER BY id DESC ")
//    fun getTrainList(): LiveData<List<Train>>

}