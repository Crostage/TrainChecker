package com.crostage.trainchecker.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.crostage.trainchecker.model.data.station.StationEntity


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
    fun insertStation(station: StationEntity)

    /**
     * Получение станций из БД
     *
     * @return список последних выбранных станций [StationEntity]
     */
    @Query("SELECT * FROM last_pick_stations")
    fun getLastStationPicks(): List<StationEntity>


}