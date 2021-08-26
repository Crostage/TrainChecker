package com.crostage.trainchecker.data.network.services

import android.util.Log
import com.crostage.trainchecker.data.model.BaseRoutesRequest
import com.crostage.trainchecker.data.model.rout.RouteResultVol2
import com.crostage.trainchecker.data.model.rout.RoutesResult
import com.crostage.trainchecker.data.model.rout.TrainStop
import com.crostage.trainchecker.data.model.rout.error.RouteRequestError
import com.crostage.trainchecker.data.model.train.Train
import com.crostage.trainchecker.data.network.ApiRequests
import com.crostage.trainchecker.domain.network.IRouteService
import com.crostage.trainchecker.utils.Helper.Companion.executeAndExceptionChek
import com.google.gson.Gson
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

    override fun getRouteList(train: Train): List<TrainStop> {

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
                val rid = body.requestId

                Thread.sleep(1000)


                val response1 =
                    retrofitApi.getResultFromRoutesRid(requestId = rid).executeAndExceptionChek()


                //todo поправить код ниже, ужасная портянка вышла
                val data = response1?.body()
                val gson = Gson()

                var routesResult: RoutesResult? = null
                var routeResultVol2: RouteResultVol2? = null
                val errorResult: RouteRequestError?

                //проверка на первый ответ
                try {
                    routesResult = gson.fromJson(data, RoutesResult::class.java)
                } catch (e: RuntimeException) {
                }

                if (routesResult?.response?.routes?.routList == null) {
                    try {
                        //проверка на второй ответ
                        routeResultVol2 = gson.fromJson(data, RouteResultVol2::class.java)
                        routeResultVol2.response
                            ?.routes?.get(0)?.routList?.let { list -> return list }
                    } catch (e: RuntimeException) {
                    }

                    if (routeResultVol2?.response?.routes?.get(0)?.routList == null) {
                        try {
                            //проверка на третий ответ
                            errorResult = gson.fromJson(data, RouteRequestError::class.java)
                            throw Exception(errorResult.response?.error?.content.toString())
                        } catch (e: RuntimeException) {
                        }
                    }

                } else {
                    routesResult.response?.routes?.routList?.let { list -> return list }
                }

            }
        }

        return mutableListOf()
    }

}