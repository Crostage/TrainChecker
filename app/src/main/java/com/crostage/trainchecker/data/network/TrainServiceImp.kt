package com.crostage.trainchecker.data.network

import android.util.Log
import com.crostage.trainchecker.data.model.BaseRoutesRequest
import com.crostage.trainchecker.data.model.BaseSearchRequest
import com.crostage.trainchecker.data.model.routRequset.RoutesResult
import com.crostage.trainchecker.data.model.routRequset.TrainStop
import com.crostage.trainchecker.data.model.trainRequest.SearchResult
import com.crostage.trainchecker.data.model.trainRequest.Train
import com.crostage.trainchecker.utils.Constant
import kotlinx.coroutines.delay
import retrofit2.await
import retrofit2.awaitResponse
import java.util.*

class TrainServiceImp : TrainService {

    companion object {
        private const val TAG = "TrainServiceImp"
    }

    private val retrofitApi = RetrofitBuilder.getApi()

    override  fun getTrainList(codeFrom: Int, codeTo: Int, date: String): List<Train> {

        val response =
            retrofitApi.getTrains(
                layerId = Constant.TRAIN_LAYER_ID,
                codeFrom = codeFrom,
                codeTo = codeTo,
                date = date
            )
                .execute()

        if (response.isSuccessful) {

            val body = response.body() as BaseSearchRequest
            val rid = body.RID
            val data = getResponseFromId(rid, Constant.TRAIN_LAYER_ID)

            val l = data?.tp?.get(0)?.list
            l?.let { return l }

        }
        return mutableListOf()
    }

    private fun getResponseFromId(rid: Long, layerId: Int): SearchResult? {

        var data: SearchResult? = null

        val response1 =
            retrofitApi.getResultFromSearchRid(
                layerId = layerId,
                requestId = rid
            )
                .execute()
        if (response1.isSuccessful) {
            data = response1.body() as SearchResult
            val responseResult = data.result

            if (responseResult == "RID") {

                Thread.sleep(3000)  //сервер сразу не обрабатывает запросы, пришлось воткнуть задержку

                val response2 =
                    retrofitApi.getResultFromSearchRid(
                        layerId = layerId,
                        requestId = rid
                    ).execute()

                if (response2.isSuccessful) {
                    data = response2.body() as SearchResult
                }
            }
        }
        return data
    }

    private  fun getResponseFromRotesId(rid: Long, layerId: Int): RoutesResult? {

        var data: RoutesResult? = null

        val response1 =
            retrofitApi.getResultFromRoutesRid(
                layerId = layerId,
                requestId = rid
            )
                .execute()


        if (response1.isSuccessful) {
            data = response1.body() as RoutesResult
            Log.d(TAG, "body1= ${response1.body()}")
            Thread.sleep(1000)

            val response2 =
                retrofitApi.getResultFromRoutesRid(
                    layerId = layerId,
                    requestId = rid
                ).execute()

            if (response2.isSuccessful) {
                data = response2.body() as RoutesResult
            }
        }
        return data
    }

    override  fun getStationCode(stationName: String): Int {
        var result = 0

        val name = stationName
            .uppercase(Locale.getDefault())
            .trim()

        val response = retrofitApi.getStation(stationName = name).execute()

        if (response.isSuccessful) {
            val data = response.body()
            if (data != null) {
                for (station in data) {
                    if (station.stationName == name) {
                        result = station.stationCode
                    }
                }
            }
        }

        return result
    }

    override  fun getTrainRoutes(train: Train): List<TrainStop> {
        val response =
            retrofitApi.getRouters(
                date = train.dateStart,
                number = train.trainNumber
            ).execute()

        if (response.isSuccessful) {
            val body = response.body() as BaseRoutesRequest
            val rid = body.rid
            val data = getResponseFromRotesId(rid, Constant.ROUTES_LAYER_ID)
            val l = data?.response?.routes?.routList
            l?.let { return l }

        }
        return mutableListOf()
    }
}