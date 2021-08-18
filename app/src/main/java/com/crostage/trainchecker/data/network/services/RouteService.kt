package com.crostage.trainchecker.data.network.services

import com.crostage.trainchecker.data.network.ApiRequests
import com.crostage.trainchecker.domain.network.IRouteService
import com.crostage.trainchecker.model.data.BaseRoutesRequest
import com.crostage.trainchecker.model.data.rout.RoutesResult
import com.crostage.trainchecker.model.data.rout.TrainStop
import com.crostage.trainchecker.model.data.train.Train
import com.crostage.trainchecker.utils.Helper.Companion.executeAndExceptionChek
import javax.inject.Inject

class RouteService @Inject constructor(private val retrofitApi: ApiRequests) : IRouteService {
    override fun getRouteList(train: Train): List<TrainStop> {

        val response = retrofitApi.getRouters(
            date = train.dateStart, number = train.trainNumber
        ).executeAndExceptionChek()

        response?.let {

            if (it.isSuccessful) {
                val body = it.body() as BaseRoutesRequest
                val rid = body.requestId
                val data = getResponseFromRotesId(rid)
                val l = data?.response?.routes?.routList
                l?.let { return l }

            }
        }
        return mutableListOf()
    }

    private fun getResponseFromRotesId(rid: Long): RoutesResult? {

        var data: RoutesResult? = null

        Thread.sleep(1000)

        val responseRoute = retrofitApi.getResultFromRoutesRid(requestId = rid
        ).executeAndExceptionChek()

        responseRoute?.let {

            if (responseRoute.isSuccessful) {
                data = responseRoute.body() as RoutesResult
            }

        }
        return data
    }
}