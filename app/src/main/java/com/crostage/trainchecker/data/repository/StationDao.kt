package com.crostage.trainchecker.data.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.crostage.trainchecker.data.model.station.StationSearchResponse

/**
 * Кэширование поиска по станциям, Room
 *
 */
@Dao
interface StationDao {

    /**
     * Запись станций в БД
     *
     * @param response
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStationResponse(response: StationSearchResponse)

    /**
     * Получение станций из БД
     *
     * @param name имя станции
     * @return сохраненный ответ от сервера [StationSearchResponse]
     */

    @Query("SELECT * FROM station_search WHERE name=:name")
    fun getListFromName(name: String): StationSearchResponse


}