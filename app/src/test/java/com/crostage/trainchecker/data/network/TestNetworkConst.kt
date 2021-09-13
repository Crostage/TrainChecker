package com.crostage.trainchecker.data.network

import com.crostage.trainchecker.data.model.GeneralResult
import com.crostage.trainchecker.data.model.rid.RouteRidResult
import com.crostage.trainchecker.data.model.rid.SeatRidResult
import com.crostage.trainchecker.data.model.rout.RoutesError
import com.crostage.trainchecker.data.model.station.StationEntity
import com.crostage.trainchecker.domain.model.Station
import com.crostage.trainchecker.domain.model.Train

class TestNetworkConst {

    companion object {
        val TRAIN = Train(
            "test",
            "test",
            listOf(),
            100,
            200,
            "МОСКВА",
            "СОЧИ",
            "21.10.2021",
            "20.10.2021",
            "1993",
            "12:20",
            "14:35",
            "40:05",
            false

        )
        const val RID = 100500L

        val ROUTE_RID_RESULT = RouteRidResult(RID)
        val SEAT_RID_RESULT = SeatRidResult(RID)
        val GENERAL_RID_RESULT = GeneralResult(null, null, null, RID)

        val ROUTES_ERROR = RoutesError("Error")

        const val STATION_NAME = "МОСКВА"

        val STATION_ENTITY_MOSCOW = StationEntity(100, "МОСКВА")
        val STATION_ENTITY_SOCHI = StationEntity(200, "СОЧИ")

        val LIST_STATION_ENTITY = listOf(
            STATION_ENTITY_MOSCOW,
            STATION_ENTITY_SOCHI
        )
        val STATION_MOSCOW = Station(100, "МОСКВА")
        val STATION_SOCHI = Station(200, "СОЧИ")

        val LIST_STATION = listOf(
            STATION_MOSCOW,
            STATION_SOCHI
        )

    }
}