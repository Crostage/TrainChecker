package com.crostage.trainchecker.data.network.services

import com.crostage.trainchecker.data.network.ApiRequests
import com.crostage.trainchecker.domain.network.ISeatService
import com.crostage.trainchecker.model.data.BaseResult
import com.crostage.trainchecker.model.data.seat.Car
import com.crostage.trainchecker.model.data.seat.SeatResult
import com.crostage.trainchecker.model.data.train.Train
import com.crostage.trainchecker.utils.Helper.Companion.executeAndExceptionChek
import javax.inject.Inject

class SeatService @Inject constructor(private val retrofitApi: ApiRequests) : ISeatService {

    override fun getSeats(train: Train): List<Car> {
        val responseRid = retrofitApi.getSeats(
            codeFrom = train.codeStationFrom,
            codeTo = train.codeStationTo,
            date = train.dateStart,
            time = train.timeStart,
            number = train.trainNumber

        ).executeAndExceptionChek()

        responseRid?.let {

            if (it.isSuccessful) {

                val body = it.body() as BaseResult
                val rid = body.requestId
                val data = getResponseFromSeatId(rid)

                val l = data?.lst?.get(0)?.cars
                l?.let { return l }

            }
        }
        return mutableListOf()
    }

    private fun getResponseFromSeatId(rid: Long): SeatResult? {

        var data: SeatResult? = null

        Thread.sleep(1000)

        val responseSeat = retrofitApi.getResultFromSeatRid(requestId = rid
        ).executeAndExceptionChek()

        responseSeat?.let {

            if (responseSeat.isSuccessful) {
                data = responseSeat.body() as SeatResult
            }
        }
        return data
    }

}