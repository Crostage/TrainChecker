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
 * @property converter конвертер моделей между слоями
 */

class StationService @Inject constructor(
    private val retrofitApi: ApiRequests,
    private val converter: IConverter<StationEntity, Station>,
) : IStationService {
    override fun getStationList(stationName: String): List<Station> {
        val name = stationName.uppercase(Locale.getDefault()).trim()

        val response = retrofitApi.getStation(stationName = name).executeAndExceptionChek()

        response?.let {

            if (it.isSuccessful) {
                val data = it.body()
                if (data != null) {
                    return data.map { entity -> converter.convert(entity) }
                }
            }
        }

        return mutableListOf()
    }
}