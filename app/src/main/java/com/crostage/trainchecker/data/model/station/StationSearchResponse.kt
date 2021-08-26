package com.crostage.trainchecker.data.model.station

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "station_search")
data class StationSearchResponse(
    @PrimaryKey
    val name: String,
    val stationList: List<Station>
)