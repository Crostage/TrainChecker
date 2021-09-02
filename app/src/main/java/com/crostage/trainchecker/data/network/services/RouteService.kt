package com.crostage.trainchecker.data.network.services

import android.util.Log
import com.crostage.trainchecker.data.network.ApiRequests
import com.crostage.trainchecker.data.network.NetworkUtil
import com.crostage.trainchecker.domain.network.IRouteService
import com.crostage.trainchecker.model.data.BaseRoutesRequest
import com.crostage.trainchecker.model.data.rout.RoutesResult
import com.crostage.trainchecker.model.data.rout.TrainStop
import com.crostage.trainchecker.model.data.seat.SeatResult
import com.crostage.trainchecker.model.data.train.TrainEntity
import com.crostage.trainchecker.utils.Helper.Companion.executeAndExceptionChek
import javax.inject.Inject


/**
 *
 * Реализация [IRouteService]
 *
 * @property retrofitApi класс для работы с сетью
 */

class RouteService @Inject constructor(private val retrofitApi: ApiRequests) : IRouteService {

    companion object {
        private const val TAG = "RouteService"
    }

    override fun getRouteListRequestId(train: TrainEntity): Long? {

        var rid: Long? = null
        val response = retrofitApi.getRouters(
            date = train.dateStart,
            number = train.trainNumber
        ).executeAndExceptionChek()

        response?.let {
            Log.d(TAG, "ROUTE")
            Log.d(TAG, "${response.raw()}")
            Log.d(TAG, "${response.body()}")
            Log.d(TAG, response.message())

            if (it.isSuccessful) {
                val body = it.body() as BaseRoutesRequest
                rid = body.requestId
            }

        }
        return rid
    }

    override fun getResultFormRoutesId(rid: Long): List<TrainStop> {
        var stopsList: List<TrainStop> = mutableListOf()
        val data = NetworkUtil.getResponseFromId(rid, retrofitApi, RoutesResult::class.java)

        val l = data?.response?.routes



        l?.let { stopsList = l }
        return stopsList
    }


}
