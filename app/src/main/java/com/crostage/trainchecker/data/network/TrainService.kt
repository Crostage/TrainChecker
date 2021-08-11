package com.crostage.trainchecker.data.network

import android.util.Log
import com.crostage.trainchecker.data.model.BaseRequest
import com.crostage.trainchecker.data.model.BaseRoutesRequest
import com.crostage.trainchecker.data.model.routRequset.RoutesResult
import com.crostage.trainchecker.data.model.routRequset.TrainStop
import com.crostage.trainchecker.data.model.stationRequest.Station
import com.crostage.trainchecker.data.model.trainRequest.SearchResult
import com.crostage.trainchecker.data.model.trainRequest.Train
import com.crostage.trainchecker.utils.Constant
import com.crostage.trainchecker.utils.Error401
import com.crostage.trainchecker.utils.Error404
import com.crostage.trainchecker.utils.ErrorConnections
import retrofit2.Call
import retrofit2.Response
import java.util.*

class TrainService : ITrainService {

    companion object {
        private const val TAG = "TrainServiceImp"
    }

    private val retrofitApi = RetrofitBuilder.getApi()

    override fun getTrainList(codeFrom: Int, codeTo: Int, date: String): List<Train> {

        val response =
            retrofitApi.getTrains(
                layerId = Constant.TRAIN_LAYER_ID,
                codeFrom = codeFrom,
                codeTo = codeTo,
                date = date
            )
                .executeAndExceptionChek()

        response?.let {

            if (it.isSuccessful) {

                val body = it.body() as BaseRequest
                val rid = body.requestId
                val data = getResponseFromId(rid)

                val l = data?.tp?.get(0)?.list
                l?.let { return l }

            }
        }
        return mutableListOf()
    }

    private fun getResponseFromId(rid: Long): SearchResult? {

        var data: SearchResult? = null

        val response1 =
            retrofitApi.getResultFromSearchRid(
                layerId = Constant.TRAIN_LAYER_ID,
                requestId = rid
            ).executeAndExceptionChek()

        response1?.let {
            if (it.isSuccessful) {
                data = it.body() as SearchResult
                val responseResult = data?.result

                if (responseResult == "RID") {

                    Thread.sleep(2000)  //сервер сразу не обрабатывает запросы, пришлось воткнуть задержку

                    val response2 =
                        retrofitApi.getResultFromSearchRid(
                            layerId = Constant.TRAIN_LAYER_ID,
                            requestId = rid
                        ).executeAndExceptionChek()


                    response2?.let {

                        if (response2.isSuccessful) {
                            data = response2.body() as SearchResult
                        }
                    }
                }
            }
        }
        return data
    }

    private fun getResponseFromRotesId(rid: Long): RoutesResult? {

        var data: RoutesResult? = null

        val response1 =
            retrofitApi.getResultFromRoutesRid(
                layerId = Constant.ROUTES_LAYER_ID,
                requestId = rid
            ).executeAndExceptionChek()


        response1?.let {

            if (it.isSuccessful) {
                data = it.body() as RoutesResult
                Log.d(TAG, "body1= ${response1.body()}")

                Thread.sleep(1000)

                val response2 =
                    retrofitApi.getResultFromRoutesRid(
                        layerId = Constant.ROUTES_LAYER_ID,
                        requestId = rid
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

    override fun getStationCode(stationName: String): List<Station>? {

        val name = stationName
            .uppercase(Locale.getDefault())
            .trim()

        val response = retrofitApi.getStation(stationName = name)
            .executeAndExceptionChek()

        response?.let {

            if (it.isSuccessful) {
                val data = it.body()
                if (data != null) {
                   return  data
                }
            }
        }

        return null
    }

    override fun getTrainRoutes(train: Train): List<TrainStop> {
        val response =
            retrofitApi.getRouters(
                date = train.dateStart,
                number = train.trainNumber
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

    private fun <T> Call<T>.executeAndExceptionChek(): Response<T>? {
        try {
            val response = execute()
            when (response.code()) {
                200 -> return response
                401 -> throw Error401()
                404 -> throw Error404()

            }
        } catch (e: Exception) {
            throw ErrorConnections()
        }
        return null
    }
}