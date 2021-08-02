package com.crostage.trainchecker.data.network

import android.util.Log
import com.crostage.trainchecker.data.model.BaseRequest
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

        Log.d(
            TAG,
            "response= $response"
        )

        if (response.isSuccessful) {
            val body = response.body() as BaseRequest

            Log.d(
                TAG,
                "body= ${response.body()}"
            )

            var rid = body.RID

            Thread.sleep(3000)
            val response1 =
                retrofitApi.getRequestFromRid(layerId = Constant.TRAIN_LAYER_ID, requestId = rid)
                    .awaitResponse()

            Log.d(
                TAG,
                "response1= $response1"
            )


            if (response1.isSuccessful) {
                val data = response1.body()
                 val responseResult = (data as SearchResult).result

                Log.d(
                    TAG,
                    "body1= ${response1.body()}"
                )


                if (responseResult == "RID") {
                    Thread.sleep(3000) //todo сделасть последовательные функции
                    val response2 =
                        retrofitApi.getRequestFromRid(
                            layerId = Constant.TRAIN_LAYER_ID,
                            requestId = rid
                        )
                            .awaitResponse()

                    Log.d(
                        TAG,
                        "response2= $response2"
                    )


                    if (response2.isSuccessful) {
                        val data1 = response2.body()
                        val l = data1?.tp?.get(0)?.list
                        l?.let { return l }

                    }


                } else if (responseResult == "OK") {
                    val l = data?.tp?.get(0)?.list
                    l?.let { return l }
                }

            }
        }
        return mutableListOf()
    }

    override suspend fun getStationCode(stationName: String): Int {
        var result = 0

        val name = stationName
            .uppercase(Locale.getDefault())
            .trim()


        val response =
            retrofitApi.getStations(
                stationName = name
            )
                .awaitResponse()
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
}