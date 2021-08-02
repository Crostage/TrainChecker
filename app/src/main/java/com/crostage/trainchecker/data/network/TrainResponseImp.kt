package com.crostage.trainchecker.data.network

import com.crostage.trainchecker.data.model.trainRequest.Train
import retrofit2.awaitResponse
import java.util.*

class TrainResponseImp : TrainResponses {

    private val retrofitApi = RetrofitBuilder.getApi()


    override suspend fun getTrainList(codeFrom: Int, codeTo: Int, date: String): List<Train> {

        val response =
            retrofitApi.getTrains(codeFrom = codeFrom, codeTo = codeTo, date = date)
                .awaitResponse()
        if (response.isSuccessful) {
            val data = response.body()

            val l = data?.tp?.get(0)?.list

            l?.let { return l }

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