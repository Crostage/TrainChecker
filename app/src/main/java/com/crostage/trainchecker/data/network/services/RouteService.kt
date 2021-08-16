package com.crostage.trainchecker.data.network.services

import com.crostage.trainchecker.data.network.ApiRequests
import com.crostage.trainchecker.data.network.RetrofitBuilder
import com.crostage.trainchecker.domain.network.IRouteService
import com.crostage.trainchecker.model.data.BaseRoutesRequest
import com.crostage.trainchecker.model.data.rout.RoutesResult
import com.crostage.trainchecker.model.data.rout.TrainStop
import com.crostage.trainchecker.model.data.train.Train
import com.crostage.trainchecker.utils.Constant
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

        val response1 = retrofitApi.getResultFromRoutesRid(
            layerId = Constant.ROUTES_LAYER_ID, requestId = rid
        ).executeAndExceptionChek()


        response1?.let {

            if (it.isSuccessful) {
                data = it.body() as RoutesResult

                Thread.sleep(1000)

                val response2 = retrofitApi.getResultFromRoutesRid(
                    layerId = Constant.ROUTES_LAYER_ID, requestId = rid
                ).executeAndExceptionChek()

                response2?.let {

                    if (response2.isSuccessful) {
                        data = response2.body() as RoutesResult
                    }
                }
            }
        }
        return data
    }
}