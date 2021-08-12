package com.crostage.trainchecker.data.network.services

import com.crostage.trainchecker.data.network.RetrofitBuilder
import com.crostage.trainchecker.model.station.Station
import com.crostage.trainchecker.utils.Helper.Companion.executeAndExceptionChek
import java.util.*

class StationService : IStationService {
    override fun getStationList(stationName: String): List<Station> {
        val retrofitApi = RetrofitBuilder.getApi
        val name = stationName.uppercase(Locale.getDefault()).trim()

        val response = retrofitApi.getStation(stationName = name).executeAndExceptionChek()

        response?.let {

            if (it.isSuccessful) {
                val data = it.body()
                if (data != null) {
                    return data
                }
            }
        }

        return mutableListOf()
    }
}