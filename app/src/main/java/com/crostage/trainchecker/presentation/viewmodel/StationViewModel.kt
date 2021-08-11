package com.crostage.trainchecker.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.crostage.trainchecker.data.model.stationRequest.Station
import com.crostage.trainchecker.data.network.ITrainService
import com.crostage.trainchecker.data.repository.TrainRepository

class StationViewModel(
    private val repository: TrainRepository,
    private val responses: ITrainService
) : ViewModel() {


   private fun getStationsCode(stationName: String): Int? {

        //получение станций из кэша (бд)
        var code: Int? = null
        val stations = repository.getStationList().toMutableList()

        //получение кода станции из бд
        for (st in stations) {
            if (st.stationName == stationName) {
                code = st.stationCode
                break
            }
        }

        //получение кодов станции если их нет в бд
//        if (code == null)
//            code = responses.getStationCode(stationName)
        return code
    }

    private fun gatStationList(){

    }

}