package com.crostage.trainchecker.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.crostage.trainchecker.data.model.trainRequest.Train
import com.crostage.trainchecker.data.network.ITrainService
import com.crostage.trainchecker.data.repository.TrainRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*

class TrainViewModel(
    private val repository: TrainRepository,
    private val responses: ITrainService
) : ViewModel() {

    private var _trains = MutableLiveData<List<Train>>()
    val trains: LiveData<List<Train>> = _trains

    private var _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    private var _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean> = _progress


    fun trainsFromSearchRequest(nameFrom: String, nameTo: String, date: String) {

        val from = nameFrom.uppercase(Locale.getDefault()).trim()

        val to = nameTo.uppercase(Locale.getDefault()).trim()

        Single.fromCallable {
            val codeFrom = getStationsCode(from)
            val codeTo = getStationsCode(to)
            getTrains(codeTo, codeFrom, date)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { _progress.value = false }
            .doOnSubscribe { _progress.value = true }
            .subscribe(
                _trains::setValue,
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
        if (code == null)
            code = responses.getStationCode(stationName)
        return code
    }

    private fun getTrains(codeTo: Int?, codeFrom: Int?, date: String): List<Train>? {
        if (codeTo != null && codeFrom != null) {
            return responses.getTrainList(codeFrom, codeTo, date)

        }
        return null
    }

}