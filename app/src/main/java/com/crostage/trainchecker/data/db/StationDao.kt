package com.crostage.trainchecker.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.crostage.trainchecker.data.model.station.Station
import com.crostage.trainchecker.data.model.station.StationSearchResponse


/**
 * Кэширование последних выбраных станций, Room
 *
 */
@Dao
interface StationDao {

    /**
     * Запись станции в БД
     *
     * @param station
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStation(station: Station)

    /**
     * Получение станций из БД
     *
     * @return список последних выбранных станций [Station]
     */
    @Query("SELECT * FROM last_pick_stations")
    fun getLastStationPicks(): List<Station>


}