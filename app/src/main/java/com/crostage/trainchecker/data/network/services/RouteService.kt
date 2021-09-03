package com.crostage.trainchecker.data.network.services

import android.util.Log
import com.crostage.trainchecker.data.converter.IConverter
import com.crostage.trainchecker.data.network.ApiRequests
import com.crostage.trainchecker.data.network.NetworkUtil
import com.crostage.trainchecker.data.network.NetworkUtil.Companion.executeAndExceptionChek
import com.crostage.trainchecker.domain.network.IRouteService
import com.crostage.trainchecker.data.model.rid.RouteRidResult
import com.crostage.trainchecker.data.model.rout.RoutesResult
import com.crostage.trainchecker.data.model.rout.TrainStopDto
import com.crostage.trainchecker.domain.model.Train
import com.crostage.trainchecker.domain.model.TrainStop
import javax.inject.Inject


/**
 *
 * Реализация [IRouteService]
 *
 * @property retrofitApi класс для работы с сетью
 */

class RouteService @Inject constructor(
    private val retrofitApi: ApiRequests,
    private val converter: IConverter<List<TrainStopDto>, List<TrainStop>>,
) : IRouteService {

    companion object {
        private const val TAG = "RouteService"
    }

    override fun getRouteListRequestId(train: Train): Long? {

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
                val body = it.body() as RouteRidResult
                rid = body.requestId
            }

        }
        return rid
    }

    override fun getResultFormRoutesId(rid: Long): List<TrainStop> {
        var stopsList: List<TrainStop> = mutableListOf()
        val data = NetworkUtil.getResponseFromId(rid, retrofitApi, RoutesResult::class.java)

        val stopDtoList = data?.response?.routes


        stopDtoList?.let { stopsList = converter.convert(stopDtoList) }
        return stopsList
    }


}
