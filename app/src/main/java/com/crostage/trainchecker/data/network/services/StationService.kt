package com.crostage.trainchecker.data.network.services

import com.crostage.trainchecker.data.converter.IConverter
import com.crostage.trainchecker.data.network.ApiRequests
import com.crostage.trainchecker.data.network.util.NetworkUtil.Companion.executeAndExceptionChek
import com.crostage.trainchecker.domain.network.IStationService
import com.crostage.trainchecker.data.model.station.StationEntity
import com.crostage.trainchecker.domain.model.Station
import java.util.*
import javax.inject.Inject

/**
 *
 * Реализация [IStationService]
 *
 * @property retrofitApi класс для работы с сетью
 * @property converter конвертер для списков станкций
 */

class StationService @Inject constructor(
    private val retrofitApi: ApiRequests,
    private val converter: IConverter<List<StationEntity>, List<Station>>,
) : IStationService {

    /**
     * @see IStationService.getStationList
     */
    override fun getStationList(stationName: String): List<Station> {
        val name = stationName.uppercase(Locale.getDefault()).trim()

        val response = retrofitApi.getStation(stationName = name).executeAndExceptionChek()

        response.let {

            if (it.isSuccessful) {
                val data = it.body()
                if (data != null) {
                    return converter.convert(data)
                }
            }
        }

        return mutableListOf()
    }
}