package com.crostage.trainchecker.data.network.services

import Seat
import com.crostage.trainchecker.data.network.ApiRequests
import com.crostage.trainchecker.domain.network.ISeatService
import com.crostage.trainchecker.model.data.rout.RoutesResult
import com.crostage.trainchecker.model.data.train.Train
import com.crostage.trainchecker.utils.Constant
import com.crostage.trainchecker.utils.Helper.Companion.executeAndExceptionChek

class SeatService(private val retrofitApi: ApiRequests) : ISeatService {
    override fun getSeats(train: Train): List<Seat> {
        TODO()
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