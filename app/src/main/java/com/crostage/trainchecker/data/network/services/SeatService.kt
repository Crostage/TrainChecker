package com.crostage.trainchecker.data.network.services

import android.util.Log
import com.crostage.trainchecker.data.converter.IConverter
import com.crostage.trainchecker.data.network.ApiRequests
import com.crostage.trainchecker.data.network.NetworkUtil
import com.crostage.trainchecker.domain.network.ISeatService
import com.crostage.trainchecker.model.data.BaseResult
import com.crostage.trainchecker.model.data.seat.Car
import com.crostage.trainchecker.model.data.seat.SeatResult
import com.crostage.trainchecker.model.data.train.TrainEntity
import com.crostage.trainchecker.model.domain.Train
import com.crostage.trainchecker.utils.Helper.Companion.executeAndExceptionChek
import javax.inject.Inject

/**
 * Реализация [ISeatService]
 *
 * @property retrofitApi класс для работы с сетью
 */

class SeatService @Inject constructor(
    private val retrofitApi: ApiRequests,
    private val converter: IConverter<TrainEntity, Train>,
) : ISeatService {
    companion object {
        private const val TAG = "SeatService"
    }

    override fun getSeatsRid(train: Train): Long? {
        var rid: Long? = null
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
                rid = body.requestId
            }
        }
        return rid
    }


    override fun getSeatsList(rid: Long): List<Car> {
        var carList: List<Car> = mutableListOf()
        val data = NetworkUtil.getResponseFromId(rid, retrofitApi, SeatResult::class.java)

        val l = data?.lst?.get(0)?.cars
        l?.let { carList = l }
        return carList
    }

}