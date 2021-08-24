package com.crostage.trainchecker.data.network.services

import com.crostage.trainchecker.data.model.BaseRoutesRequest
import com.crostage.trainchecker.data.model.rout.RoutesResult
import com.crostage.trainchecker.data.model.rout.TrainStop
import com.crostage.trainchecker.data.model.train.Train
import com.crostage.trainchecker.data.network.ApiRequests
import com.crostage.trainchecker.domain.network.IRouteService
import com.crostage.trainchecker.utils.Helper.Companion.executeAndExceptionChek
import com.crostage.trainchecker.utils.NetworkUtil
import javax.inject.Inject

/**
 *
 * Реализация [IRouteService]
 *
 * @property retrofitApi класс для работы с сетью
 */

class RouteService @Inject constructor(private val retrofitApi: ApiRequests) : IRouteService {

    override fun getRouteList(train: Train): List<TrainStop> {

        val response = retrofitApi.getRouters(
            date = train.dateStart,
            number = train.trainNumber
        ).executeAndExceptionChek()

        response?.let {

            if (it.isSuccessful) {
                val body = it.body() as BaseRoutesRequest
                val rid = body.requestId
                val data = NetworkUtil.getResponseFromId(rid, retrofitApi, RoutesResult::class.java)
                val l = data?.response?.routes?.routList
                l?.let { return l }

            }
        }
        return mutableListOf()
    }

}