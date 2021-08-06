package com.crostage.trainchecker.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crostage.trainchecker.data.model.trainRequest.Train
import com.crostage.trainchecker.data.network.TrainService
import com.crostage.trainchecker.data.repository.TrainRepository
import kotlinx.coroutines.launch
import java.util.*

class TrainViewModel(
    private val repository: TrainRepository,
    private val responses: TrainService
) : ViewModel() {

    private var _trains = MutableLiveData<List<Train>>()
    val trains: LiveData<List<Train>> = _trains

    private var _error = MutableLiveData<Exception>()
    val error: LiveData<java.lang.Exception> = _error


    fun trainsFromSearchRequest(nameFrom: String, nameTo: String, date: String) {

        val from = nameFrom.uppercase(Locale.getDefault()).trim()

        val to = nameTo.uppercase(Locale.getDefault()).trim()

        var codeFrom: Int? = null
        var codeTo: Int? = null

        viewModelScope.launch {

            //получение станций из кэша (бд)
            val stations = repository.getStationList().toMutableList()

            //получение номера станции из бд
            for (st in stations) {
                if (codeFrom != null && codeTo != null)
                    break
                if (st.stationName == from) {
                    codeFrom = st.stationCode
                }
                if (st.stationName == to) {
                    codeTo = st.stationCode
                }
            }

            try {
                //получение кодов станции если их нет в бд
                if (codeFrom == null)
                    codeFrom = responses.getStationCode(nameFrom)
                if (codeTo == null)
                    codeTo = responses.getStationCode(nameTo)

                if (codeTo != null && codeFrom != null) {
                    val trainList = responses.getTrainList(codeFrom!!, codeTo!!, date)
                    _trains.postValue(trainList)
                }
            } catch (e: Exception) {
                _error.postValue(e)
            }

        }

    }


    private fun getStationFromName(stationName: String) {

    }


}