package com.crostage.trainchecker.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.crostage.trainchecker.data.model.station.StationSearchResponse
import com.crostage.trainchecker.utils.Constant.Companion.TABLE_NAME_STATION_SEARCH

/**
 * Закэшированные данные поиска по станциям, Room
 *
 */
@Dao
interface StationResponseDao {

    /**
     * Запись запроса станций в БД
     *
     * @param response ответ от сервера на поисковый запрос [StationSearchResponse]
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStationResponse(response: StationSearchResponse)

    /**
     * Получение станций по запросу из БД
     *
     * @param name запрос
     * @return сохраненный ответ от сервера [StationSearchResponse]
     */

    @Query("SELECT * FROM $TABLE_NAME_STATION_SEARCH WHERE name=:name")
    fun getListFromName(name: String): StationSearchResponse?


}