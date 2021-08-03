package com.crostage.trainchecker.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crostage.trainchecker.data.model.routRequset.TrainStop
import com.crostage.trainchecker.data.model.trainRequest.Train
import com.crostage.trainchecker.data.network.TrainService
import com.crostage.trainchecker.data.repository.TrainRepository
import kotlinx.coroutines.launch
import java.util.*

class TrainViewModel(
    private val repository: TrainRepository,
    private val responses: TrainService
) : ViewModel() {

    companion object {
        private const val TAG = "TrainViewModel"
    }

    private var _trains = MutableLiveData<List<Train>>()
    val trains: LiveData<List<Train>> = _trains

    private var _routes = MutableLiveData<List<TrainStop>>()
    val routes: LiveData<List<TrainStop>> = _routes

    private var _error = MutableLiveData<Exception>()
    val error: LiveData<java.lang.Exception> = _error

    fun getTrains(nameFrom: String, nameTo: String, date: String) {

        val from = nameFrom
            .uppercase(Locale.getDefault())
            .trim()

        val to = nameTo
            .uppercase(Locale.getDefault())
            .trim()

        var codeFrom: Int? = null
        var codeTo: Int? = null

        viewModelScope.launch {

            val stations = repository.getStationList().toMutableList()

            for (st in stations) {

                if (codeFrom != null && codeTo != null)
                    break

                if (st.n == from) {
                    codeFrom = st.c
                }
                if (st.n == to) {
                    codeTo = st.c
                }
            }


            try {

                if (codeFrom == null)
                    codeFrom = responses.getStationCode(nameFrom)


                if (codeTo == null)
                    codeTo = responses.getStationCode(nameTo)

                val trainList = responses.getTrainList(codeFrom!!, codeTo!!, date)

                _trains.postValue(trainList)
            } catch (e: Exception) {
                throw e
                _error.postValue(e)
            }

        }

    }

    fun getRoutes(train: Train) {
        viewModelScope.launch {
            _routes.postValue(responses.getTrainRoutes(train))
        }
    }


}