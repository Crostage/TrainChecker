package com.crostage.trainchecker.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crostage.trainchecker.adapter.TrainListAdapter
import com.crostage.trainchecker.model.stationRequest.Station
import com.crostage.trainchecker.network.RetrofitBuilder
import com.crostage.trainchecker.repository.TrainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse
import java.util.*

class TrainViewModel(private val repository: TrainRepository) : ViewModel() {


    companion object {
        private const val TAG = "TrainViewModel"
    }

    private val retrofitApi = RetrofitBuilder.getApi()

    private lateinit var stations: MutableList<Station>

    private suspend fun getStationCode(stationName: String): Int {

        var result = 0

        val name = stationName
            .uppercase(Locale.getDefault())
            .trim()

        stations = repository.getStationList().toMutableList()

        for (st in stations) {
            if (st.n == name) {
                return st.c
            }
        }

        try {
            val response =
                retrofitApi.getStations(
                    stationName = name
                )
                    .awaitResponse()
            if (response.isSuccessful) {
                val data = response.body()
                Log.d(TAG, "stationName = ${response.raw()}")

                if (data != null) {
                    for (station in data) {
                        repository.insertStation(station)
                        if (station.n == name) {
                            result = station.c
                        }
                    }


                }
            }

        } catch (e: Exception) {
            withContext(Dispatchers.Main) {

            }
        }

        return result
    }


    fun getTrains(nameFrom: String, nameTo: String, date: String, adapter: TrainListAdapter) {

        viewModelScope.launch {
            val codeFrom = getStationCode(nameFrom)
            val codeTo = getStationCode(nameTo)
            getTrainList(adapter, codeFrom, codeTo, date)
        }

    }

    private suspend fun getTrainList(
        adapter: TrainListAdapter,
        codeFrom: Int,
        codeTo: Int,
        date: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response =
                    retrofitApi.getTrains(codeFrom = codeFrom, codeTo = codeTo, date = date)
                        .awaitResponse()
                if (response.isSuccessful) {
                    val data = response.body()

                    Log.d(TAG, "trains = ${response.raw()}")
                    val l = data?.tp?.get(0)?.list

                    withContext(Dispatchers.Main) {
                        l?.let { adapter.setData(it) }
                        //убрать адаптер отсюда

                    }
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                }
            }
        }
    }


}