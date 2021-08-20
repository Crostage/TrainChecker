package com.crostage.trainchecker.data.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.crostage.trainchecker.model.domain.StationSearchResponse


@Dao
interface StationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStationResponse(response: StationSearchResponse)

    @Query("SELECT * FROM station_search WHERE name=:name")
    fun getListFromName(name: String): StationSearchResponse


}