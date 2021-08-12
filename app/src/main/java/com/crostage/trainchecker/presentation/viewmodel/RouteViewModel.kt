package com.crostage.trainchecker.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.crostage.trainchecker.model.rout.TrainStop
import com.crostage.trainchecker.model.train.Train
import com.crostage.trainchecker.data.network.services.ITrainService
import com.crostage.trainchecker.data.repository.TrainRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RouteViewModel(
    private val repository: TrainRepository,
    private val responses: ITrainService
) : ViewModel() {

    private var _routes = MutableLiveData<List<TrainStop>>()
    val routes: LiveData<List<TrainStop>> = _routes

    private var _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    private var _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean> = _progress



    fun getRoutes(train: Train) {
        Single.fromCallable {
            responses.getTrainRoutes(train).toMutableList().apply { removeAt(0) }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { _progress.value = false }
            .doOnSubscribe { _progress.value = true }
            .subscribe(
                _routes::setValue,
                _error::setValue
            )
//            { e -> throw e }
    }
}