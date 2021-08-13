package com.crostage.trainchecker.data.repository

import androidx.lifecycle.LiveData
import androidx.room.*
import com.crostage.trainchecker.model.data.station.Station
import com.crostage.trainchecker.model.domain.StationSearchResponse
import retrofit2.http.GET


@Dao
interface TrainDao {

    //    @Insert(onConflict = OnConflictStrategy.REPLACE)
//     fun insertStation(station: Station)
//
//    @Query("SELECT*FROM stations ORDER BY stationName DESC ")
//     fun getStationList(): List<Station>
    @Insert
    fun insertStationResponse(response: StationSearchResponse)

    @Query("SELECT * FROM station_search WHERE name=:name")
    fun getListFromName(name: String): StationSearchResponse


//    @Query("SELECT*FROM trains ORDER BY id DESC ")
//    fun getTrainList(): LiveData<List<Train>>

}