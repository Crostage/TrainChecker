package com.crostage.trainchecker.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.crostage.trainchecker.data.model.stationRequest.Station
import com.crostage.trainchecker.data.network.ITrainService
import com.crostage.trainchecker.data.repository.TrainRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class StationViewModel(
    private val repository: TrainRepository,
    private val responses: ITrainService
) : ViewModel() {

    private val _stations = MutableLiveData<List<Station>>()
    val stations: LiveData<List<Station>> = _stations

    private var _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    private var _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean> = _progress


    fun getStation(stationName: String) {
        Single.fromCallable {
            responses.getStationCode(stationName)
        }.map { it?.filter { station -> station.stationName.contains(stationName) } }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { _progress.value = false }
            .doOnSubscribe { _progress.value = true }
            .subscribe(
                _stations::setValue,
                _error::setValue
            )
    }


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


}