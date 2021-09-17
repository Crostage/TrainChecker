package com.crostage.trainchecker.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.crostage.trainchecker.domain.interactors.interfaces.IStationInteractor
import com.crostage.trainchecker.domain.model.Station
import com.crostage.trainchecker.presentation.model.Event
import com.crostage.trainchecker.utils.Constant.Companion.SAVED_STATE_STATIONS
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 *
 * View Model для работы со станциями отправления
 *
 * @property interactor [IStationInteractor]  бизнес-логика для работы со станциями
 * @param savedStateHandle сущность для сохранения LiveData
 */
class StationViewModel(
    private val interactor: IStationInteractor,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel() {

    private val _stations = savedStateHandle.getLiveData<List<Station>>(SAVED_STATE_STATIONS)
    val stations: LiveData<List<Station>> = _stations
    private var _resultStation = MutableLiveData<Event<Station>>()
    var resultStation: LiveData<Event<Station>> = _resultStation

    init {
        if (_stations.value == null) {
            getLastPickStations()
        }
    }

    /**
     * Получения списка станций по поисковому запросу (часть названия стацнии)
     *
     * @param stationName название станции
     */
    fun getStationResponse(stationName: String) {

        compositeDisposable.add(
            Single.fromCallable {
                interactor.getStationListFromRepo(stationName)
            }.flatMap {
                if (it.isEmpty()) {
                    interactor.getStationListFromService(stationName)
                } else Single.just(it)
            }.map {
                it.filter { station ->
                    station.stationName.contains(stationName)
                }
            }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally { _progress.value = false }
                .doOnSubscribe { _progress.value = true }
                .subscribe(
                    _stations::setValue
                ) { _error.value = Event(it) }
        )
    }


    /**
     * Добавления выбранной станции в БД для отображения последних выбранных станций
     *
     * @param station выбранная станция
     */
    private fun insertStation(station: Station) {
        compositeDisposable.add(
            Completable.fromCallable {
                interactor.insertStation(station)
            }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({}, { _error.value = Event(it) })
        )
    }


    /**
     * Получение списка последних выбранных станций
     *
     */
    private fun getLastPickStations() {
        compositeDisposable.add(
            Single.fromCallable {
                interactor.getLastStationsPick()
            }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    _stations::setValue
                ) { _error.value = Event(it) }
        )

    }


    /**
     * Обработка выбранной станции
     *
     * @param station выбранная станция
     */
    fun setResultStation(station: Station) {
        insertStation(station)
        _resultStation.postValue(Event(station))
    }

}