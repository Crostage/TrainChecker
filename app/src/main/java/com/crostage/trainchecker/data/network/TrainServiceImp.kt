package com.crostage.trainchecker.data.network

import android.util.Log
import com.crostage.trainchecker.data.model.BaseRoutesRequest
import com.crostage.trainchecker.data.model.BaseSearchRequest
import com.crostage.trainchecker.data.model.routRequset.RoutesResult
import com.crostage.trainchecker.data.model.routRequset.TrainStop
import com.crostage.trainchecker.data.model.trainRequest.SearchResult
import com.crostage.trainchecker.data.model.trainRequest.Train
import com.crostage.trainchecker.helper.Constant
import retrofit2.awaitResponse
import java.util.*

class TrainServiceImp : TrainService {

    companion object {
        private const val TAG = "TrainServiceImp"
    }

    private val retrofitApi = RetrofitBuilder.getApi()


    override suspend fun getTrainList(codeFrom: Int, codeTo: Int, date: String): List<Train> {

        val response =
            retrofitApi.getTrains(
                layerId = Constant.TRAIN_LAYER_ID,
                codeFrom = codeFrom,
                codeTo = codeTo,
                date = date
            )
                .awaitResponse()

        Log.d(TAG, "response= $response")

        if (response.isSuccessful) {
            val body = response.body() as BaseSearchRequest

            Log.d(TAG, "body= ${response.body()}")

            val rid = body.RID

            val data = getResponseFromId(rid, Constant.TRAIN_LAYER_ID)

            val l = data?.tp?.get(0)?.list
            l?.let { return l }

        }
        return mutableListOf()
    }


    private suspend fun getResponseFromId(rid: Long, layerId: Int): SearchResult? {

        var data: SearchResult? = null

        val response1 =
            retrofitApi.getResultFromSearchRid(
                layerId = layerId,
                requestId = rid
            )
                .awaitResponse()

        Log.d(TAG, "response1= $response1")

        if (response1.isSuccessful) {
            data = response1.body() as SearchResult
            val responseResult = data.result

            Log.d(TAG, "body1= ${response1.body()}")

            if (responseResult == "RID") {
                Thread.sleep(3000) //todo сделасть последовательные функции

                val response2 =
                    retrofitApi.getResultFromSearchRid(
                        layerId = layerId,
                        requestId = rid
                    ).awaitResponse()


                Log.d(TAG, "response2= $response2")

                if (response2.isSuccessful) {
                    data = response2.body() as SearchResult
                }

            }

        }
        return data
    }


    private suspend fun getResponseFromRotesId(rid: Long, layerId: Int): RoutesResult? {

        var data: RoutesResult? = null

        val response1 =
            retrofitApi.getResultFromRoutesRid(
                layerId = layerId,
                requestId = rid
            )
                .awaitResponse()

        Log.d(TAG, "response1= $response1")

        if (response1.isSuccessful) {
            data = response1.body() as RoutesResult
//            val responseResult = data.type

            Log.d(TAG, "body1= ${response1.body()}")

//            if (responseResult == "REQUEST_ID") {
            Thread.sleep(1000) //todo сделасть последовательные функции

            val response2 =
                retrofitApi.getResultFromRoutesRid(
                    layerId = layerId,
                    requestId = rid
                ).awaitResponse()


            Log.d(TAG, "response2= $response2")

            if (response2.isSuccessful) {
                data = response2.body() as RoutesResult
            }

        }

//        }
        return data
    }

    override suspend fun getStationCode(stationName: String): Int {
        var result = 0

        val name = stationName
            .uppercase(Locale.getDefault())
            .trim()

        val response = retrofitApi.getStation(stationName = name).awaitResponse()

        if (response.isSuccessful) {
            val data = response.body()
            if (data != null) {
                for (station in data) {
                    if (station.n == name) {
                        result = station.c
                    }
                }
            }
        }

        return result
    }

    override suspend fun getTrainRoutes(train: Train): List<TrainStop> {
        val response =
            retrofitApi.getRouters(
                date = train.date0,
                number = train.number
            )
                .awaitResponse()

        Log.d(TAG, "response= $response")

        if (response.isSuccessful) {
            val body = response.body() as BaseRoutesRequest

            Log.d(TAG, "body= ${response.body()}")

            val rid = body.rid

            val data = getResponseFromRotesId(rid, Constant.ROUTES_LAYER_ID)

            val l = data?.response?.routes?.routList
            l?.let { return l }

        }
        return mutableListOf()
    }
}