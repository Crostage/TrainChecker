package com.crostage.trainchecker.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.crostage.trainchecker.data.model.station.Station
import com.crostage.trainchecker.domain.interactors.interfaces.IStationInteractor
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class StationViewModel(
    private val interactor: IStationInteractor,
) : ViewModel() {

    private val _stations = MutableLiveData<List<Station>>()
    val stations: LiveData<List<Station>> = _stations

    private var _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    private var _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean> = _progress

    private val compositeDisposable = CompositeDisposable()

    fun getStationResponse(stationName: String) {

        compositeDisposable.add(
            Single.fromCallable {
                interactor.getStationList(stationName)
            }.map { it?.filter { station -> station.stationName.contains(stationName) } }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally { _progress.value = false }
                .doOnSubscribe { _progress.value = true }
                .subscribe(
                    _stations::setValue,
                    _error::setValue
                )
        )
    }

    fun insertStation(station: Station) {
        compositeDisposable.add(
            Single.fromCallable {
                interactor.insertStation(station)
            }
                .subscribeOn(Schedulers.io())
                .subscribe()
        )
    }

    fun getLastPickStations() {
        compositeDisposable.add(
            Single.fromCallable {
                interactor.getLastStationsPick()
            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally { _progress.value = false }
                .doOnSubscribe { _progress.value = true }
                .subscribe(
                    _stations::setValue,
                    _error::setValue
                )
        )
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}