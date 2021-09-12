package com.crostage.trainchecker.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.crostage.trainchecker.domain.interactors.interfaces.IStationInteractor
import com.crostage.trainchecker.domain.model.Station
import com.crostage.trainchecker.presentation.model.Event
import com.crostage.trainchecker.utils.Constant.Companion.SAVED_STATE_STATIONS
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * ViewModel для работы со станциями отправления
 *
 * @property interactor бизнес логика получения станций из сети и БД
 */

class StationViewModel(
    private val interactor: IStationInteractor,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel() {

    private val _stations = savedStateHandle.getLiveData<List<Station>>(SAVED_STATE_STATIONS)

    /**
     *  LiveData списка станций, меняется при изменении поискового запроса
     */
    val stations: LiveData<List<Station>> = _stations
    private var _resultStation = MutableLiveData<Event<Station>>()

    /**
     *  LiveData события выбора станции
     */
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

        // TODO: оставить логику тут? стоит ли разделить?
        compositeDisposable.add(
            Single.fromCallable {
                interactor.getStationListFromRepo(stationName)
            }.flatMap {
                Single.fromCallable {
                    if (it.isEmpty()) {
                        interactor.getStationListFromService(stationName)
                    } else it
                }
            }.map {
                if (it.isNotEmpty())
                    it.filter { station ->
                        station.stationName.contains(stationName)
                    }
                else listOf()

            }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally { _progress.value = false }
                .doOnSubscribe { _progress.value = true }
                .subscribe(
                    _stations::setValue,
                    _error::setValue
                )
        )
    }


    /**
     * Добавления выбранной станции в БД для отображения последних выбранных станций
     *
     * @param station выбранная станция
     */
    private fun insertStation(station: Station) {
        compositeDisposable.add(
            Single.fromCallable {
                interactor.insertStation(station)
            }
                .subscribeOn(Schedulers.computation())
                .subscribe()
        )
    }


    private fun getLastPickStations() {
        compositeDisposable.add(
            Single.fromCallable {
                interactor.getLastStationsPick()
            }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally { _progress.value = false }
                .doOnSubscribe { _progress.value = true }
                .subscribe(
                    _stations::setValue,
                    _error::setValue
                )
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