package com.crostage.trainchecker.data.network.services

import com.crostage.trainchecker.data.model.rid.SeatRidResult
import com.crostage.trainchecker.data.model.seat.CarDto
import com.crostage.trainchecker.data.network.ApiRequests
import com.crostage.trainchecker.data.network.util.NetworkUtil
import com.crostage.trainchecker.data.network.util.NetworkUtil.Companion.executeAndExceptionChek
import com.crostage.trainchecker.domain.converter.IConverter
import com.crostage.trainchecker.domain.model.Car
import com.crostage.trainchecker.domain.model.Train
import com.crostage.trainchecker.domain.network.ISeatService
import com.crostage.trainchecker.utils.Constant.Companion.SEAT_LAYER_ID
import com.crostage.trainchecker.utils.ServerSendError
import javax.inject.Inject

/**
 * Реализация [ISeatService]
 *
 * @property retrofitApi класс для работы с сетью
 */

class SeatService @Inject constructor(
    private val retrofitApi: ApiRequests,
    private val converter: IConverter<List<CarDto>, List<Car>>,
) : ISeatService {

    override fun getSeatsRid(train: Train): Long? {
        var rid: Long? = null
        val responseRid = retrofitApi.getSeats(
            codeFrom = train.codeStationFrom,
            codeTo = train.codeStationTo,
            date = train.dateStart,
            time = train.timeStart,
            number = train.trainNumber

        ).executeAndExceptionChek()

        responseRid.let {

            if (it.isSuccessful) {
                val body = it.body() as SeatRidResult
                rid = body.requestId
            }
        }
        return rid
    }


    override fun getSeatsList(rid: Long): List<Car> {
        var carList: List<Car> = listOf()
        val data = NetworkUtil.getResponseFromId(SEAT_LAYER_ID, rid, retrofitApi)

        try {
            val carDtoList = data?.listCarResponse?.get(0)?.cars
            carDtoList?.let { carList = converter.convert(carDtoList) }
        } catch (e: IndexOutOfBoundsException) {
            throw ServerSendError()
        }
        return carList
    }

}