package com.crostage.trainchecker.data.network.services

import android.util.Log
import com.crostage.trainchecker.data.network.ApiRequests
import com.crostage.trainchecker.domain.network.ISeatService
import com.crostage.trainchecker.data.model.BaseResult
import com.crostage.trainchecker.data.model.rout.RoutesResult
import com.crostage.trainchecker.data.model.seat.Car
import com.crostage.trainchecker.data.model.seat.SeatResult
import com.crostage.trainchecker.data.model.train.Train
import com.crostage.trainchecker.utils.Helper.Companion.executeAndExceptionChek
import com.crostage.trainchecker.utils.NetworkUtil
import javax.inject.Inject

/**
 * Реализация [ISeatService]
 *
 * @property retrofitApi класс для работы с сетью
 */

class SeatService @Inject constructor(private val retrofitApi: ApiRequests) : ISeatService {
    companion object {
        private const val TAG = "SeatService"
    }

    override fun getSeats(train: Train): List<Car> {
        val responseRid = retrofitApi.getSeats(
            codeFrom = train.codeStationFrom,
            codeTo = train.codeStationTo,
            date = train.dateStart,
            time = train.timeStart,
            number = train.trainNumber

        ).executeAndExceptionChek()

        responseRid?.let {

            Log.d(TAG, "SEAT")
            Log.d(TAG, "${responseRid.raw()}")
            Log.d(TAG, "${responseRid.body()}")
            Log.d(TAG, responseRid.message())
            if (it.isSuccessful) {

                val body = it.body() as BaseResult
                val rid = body.requestId
                val data = NetworkUtil.getResponseFromId(rid, retrofitApi, SeatResult::class.java)


                val l = data?.lst?.get(0)?.cars
                l?.let { return l }

            }
        }
        return mutableListOf()
    }

}