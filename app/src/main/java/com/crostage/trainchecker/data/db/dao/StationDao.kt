package com.crostage.trainchecker.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.crostage.trainchecker.data.model.station.StationEntity
import com.crostage.trainchecker.utils.Constant.Companion.TABLE_NAME_LAST_PICK

/**
 * Кэширование в БД последних выбраных станций, Room
 *
 */
@Dao
interface StationDao {

    /**
     * Запись станции в БД
     *
     * @param station станция для записи [StationEntity]
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStation(station: StationEntity)

    /**
     * Получение станций из БД
     *
     * @return список последних выбранных станций [StationEntity]
     */
    @Query("SELECT * FROM $TABLE_NAME_LAST_PICK")
    fun getLastStationPicks(): List<StationEntity>


}