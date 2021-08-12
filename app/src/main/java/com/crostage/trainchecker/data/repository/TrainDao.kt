package com.crostage.trainchecker.data.repository

import androidx.room.*
import com.crostage.trainchecker.model.station.Station


@Dao
interface TrainDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertStation(station: Station)

    @Query("SELECT*FROM stations ORDER BY stationName DESC ")
     fun getStationList(): List<Station>

//    @Query("SELECT*FROM trains ORDER BY id DESC ")
//    fun getTrainList(): LiveData<List<Train>>

}