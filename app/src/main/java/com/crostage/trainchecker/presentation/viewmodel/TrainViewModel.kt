package com.crostage.trainchecker.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.crostage.trainchecker.data.model.stationRequest.Station
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


    fun trainsFromSearchRequest(codeFrom: Int, codeTo: Int, date: String) {



        Single.fromCallable {
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


    private fun getTrains(codeTo: Int?, codeFrom: Int?, date: String): List<Train>? {
        if (codeTo != null && codeFrom != null) {
            return responses.getTrainList(codeFrom, codeTo, date)

        }
        return null
    }

}