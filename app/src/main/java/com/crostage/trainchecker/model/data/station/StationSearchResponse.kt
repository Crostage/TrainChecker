package com.crostage.trainchecker.model.data.station

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.crostage.trainchecker.utils.Constant.Companion.TABLE_NAME_STATION_SEARCH

@Entity(tableName = TABLE_NAME_STATION_SEARCH)
data class StationSearchResponse(
    @PrimaryKey
    val name: String,
    val stationList: List<StationEntity>,
)