package com.crostage.trainchecker.data.network.services

import com.crostage.trainchecker.data.network.ApiRequests
import com.crostage.trainchecker.domain.network.IStationService
import com.crostage.trainchecker.model.data.station.StationEntity
import com.crostage.trainchecker.utils.Helper.Companion.executeAndExceptionChek
import java.util.*
import javax.inject.Inject

/**
 *
 * Реализация [IStationService]
 *
 * @property retrofitApi класс для работы с сетью
 */

class StationService @Inject constructor(private val retrofitApi: ApiRequests) : IStationService {
    override fun getStationList(stationName: String): List<StationEntity> {
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