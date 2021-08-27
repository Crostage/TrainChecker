package com.crostage.trainchecker.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.crostage.trainchecker.domain.interactors.interfaces.IRouteInteractor
import com.crostage.trainchecker.model.data.rout.TrainStop
import com.crostage.trainchecker.model.data.train.TrainEntity
import com.crostage.trainchecker.model.domain.Train
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class RouteViewModel(
    private val interactor: IRouteInteractor
) : ViewModel() {

    private var _routes = MutableLiveData<List<TrainStop>>()
    val routes: LiveData<List<TrainStop>> = _routes

    private var _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    private var _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean> = _progress


    private val compositeDisposable = CompositeDisposable()

    fun getRoutes(train: Train) {

        compositeDisposable.add(
                Single.fromCallable {
                    interactor.getRouteList(train).toMutableList().apply { removeAt(0) }
                }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doFinally { _progress.value = false }
                        .doOnSubscribe { _progress.value = true }
                        .subscribe(
                                _routes::setValue,
                                _error::setValue
                        )
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}