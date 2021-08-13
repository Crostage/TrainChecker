package com.crostage.trainchecker.model.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.crostage.trainchecker.model.data.station.Station


@Entity(tableName = "station_search")
data class StationSearchResponse(
    @PrimaryKey
    val name: String,
    val stationList: List<Station>
)