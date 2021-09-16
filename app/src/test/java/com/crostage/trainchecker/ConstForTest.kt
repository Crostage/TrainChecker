package com.crostage.trainchecker

import com.crostage.trainchecker.data.model.rid.RouteRidResult
import com.crostage.trainchecker.data.model.rid.SeatRidResult
import com.crostage.trainchecker.data.model.rout.RoutesError
import com.crostage.trainchecker.data.model.rout.TrainStopDto
import com.crostage.trainchecker.data.model.seat.CarDto
import com.crostage.trainchecker.data.model.station.StationEntity
import com.crostage.trainchecker.data.model.station.StationSearchResponse
import com.crostage.trainchecker.data.model.train.FavouriteEntity
import com.crostage.trainchecker.data.model.train.TrainEntity
import com.crostage.trainchecker.domain.model.*

class ConstForTest {
    companion object {

        val CAR = Car(
            "100",
            "carType",
            "clsType",
            "3500",
            listOf(Seat(2, 3500, "у туалета"))
        )

        val LIST_CAR = listOf(CAR)

        val CAR_DTO = CarDto(
            "100",
            "carType",
            "clsType",
            "3500",
            listOf(Seat(2, 3500, "у туалета"))
        )

        val LIST_CAR_DTO = listOf(CAR_DTO)

        val FAVOURITE_ENTITY = FavouriteEntity(
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

        const val CODE_FROM = 100
        const val CODE_TO = 200
        const val DATE_START = "20.10.2021"

        val LIST_FAVOURITE_ENTITY = listOf(FAVOURITE_ENTITY)

        val TRAIN_ENTITY = TrainEntity(
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
        )

        val LIST_TRAIN_ENTITY = listOf(TRAIN_ENTITY)


        val STATION = Station(100, "МОСКВА")

        val LIST_STATION = listOf(STATION)

        val STATION_ENTITY = StationEntity(100, "МОСКВА")

        val LIST_STATION_ENTITY = listOf(STATION_ENTITY)

        val TRAIN_STOP = TrainStop(
            100,
            "14:20",
            200,
            "КУКУЕВО"
        )

        val LIST_TRAIN_STOP = listOf(TRAIN_STOP)

        val TRAIN_STOP_DTO = TrainStopDto(
            100,
            "14:20",
            200,
            "КУКУЕВО"
        )

        val LIST_TRAIN_STOP_DTO = listOf(TRAIN_STOP_DTO)

        val TICKET = Ticket(5, 200, "type", "typeLoc")

        const val JSON_TICKET =
            "[{\"freeSeats\":5,\"tariff\":200,\"type\":\"type\",\"typeLoc\":\"typeLoc\"}]"

        const val JSON_STATION_ENTITY = "[{\"c\":100,\"n\":\"МОСКВА\"}]"

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

        val TRAIN_NO_ACT_DATE = Train(
            "test",
            "test",
            listOf(),
            100,
            200,
            "МОСКВА",
            "СОЧИ",
            "08.08.2021",
            "07.08.2021",
            "1993",
            "12:20",
            "14:35",
            "40:05",
            false

        )


        val LIST_TRAIN_NO_CAT_DATE = listOf(TRAIN_NO_ACT_DATE)

        val LIST_TRAIN = listOf(TRAIN)

        const val RID = 100500L

        val ROUTE_RID_RESULT = RouteRidResult(RID)
        val SEAT_RID_RESULT = SeatRidResult(RID)

        val ROUTES_ERROR = RoutesError("Error")

        const val STATION_NAME = "МОСКВА"

        const val DATE_TEST_STRING = "16.09.2021"
        const val DATE_TEST_L = 1631785992265L

        val STATION_SEARCH_RESPONSE = StationSearchResponse(STATION_NAME,
            LIST_STATION_ENTITY)
    }
}