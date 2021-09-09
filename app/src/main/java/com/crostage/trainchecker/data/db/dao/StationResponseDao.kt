package com.crostage.trainchecker.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.crostage.trainchecker.data.model.station.StationSearchResponse
import com.crostage.trainchecker.utils.Constant.Companion.TABLE_NAME_STATION_SEARCH

/**
 * Кэширование поиска по станциям, Room
 *
 */
@Dao
interface StationResponseDao {

    /**
     * Запись запроса станций в БД
     *
     * @param response
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStationResponse(response: StationSearchResponse)

    /**
     * Получение станций по запросу из БД
     *
     * @param name имя станции
     * @return сохраненный ответ от сервера [StationSearchResponse]
     */

    @Query("SELECT * FROM $TABLE_NAME_STATION_SEARCH WHERE name=:name")
    fun getListFromName(name: String): StationSearchResponse?


}