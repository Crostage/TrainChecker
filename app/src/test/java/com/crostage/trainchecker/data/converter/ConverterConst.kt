package com.crostage.trainchecker.data.converter

import com.crostage.trainchecker.data.model.rout.TrainStopDto
import com.crostage.trainchecker.data.model.seat.CarDto
import com.crostage.trainchecker.data.model.station.StationEntity
import com.crostage.trainchecker.data.model.train.FavouriteEntity
import com.crostage.trainchecker.data.model.train.TrainEntity
import com.crostage.trainchecker.domain.model.*

class ConverterConst {
    companion object {

        val CAR = Car(
            "100",
            "carType",
            "clsType",
            "3500",
            listOf(Seat(2, 3500, "у туалета"))
        )


        val CAR_DTO = CarDto(
            "100",
            "carType",
            "clsType",
            "3500",
            listOf(Seat(2, 3500, "у туалета"))
        )

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


        val STATION = Station(100, "МОСКВА")
        val STATION_ENTITY = StationEntity(100, "МОСКВА")

        val TRAIN_STOP = TrainStop(
            100,
            "14:20",
            200,
            "КУКУЕВО"
        )

        val TRAIN_STOP_DTO = TrainStopDto(
            100,
            "14:20",
            200,
            "КУКУЕВО"
        )


        val TICKET = Ticket(5, 200, "type", "typeLoc")

        const val JSON_TICKET =
            "[{\"freeSeats\":5,\"tariff\":200,\"type\":\"type\",\"typeLoc\":\"typeLoc\"}]"

        const val JSON_STATION_ENTITY = "[{\"c\":100,\"n\":\"МОСКВА\"}]"

    }
}