package com.crostage.trainchecker.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import com.crostage.trainchecker.domain.interactors.interfaces.IRouteInteractor
import com.crostage.trainchecker.domain.model.Train
import com.crostage.trainchecker.domain.model.TrainStop
import com.crostage.trainchecker.presentation.model.Event
import com.crostage.trainchecker.utils.Constant.Companion.SAVED_STATE_STOPS
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * View Model экрана маршрута поезда
 *
 * @property interactor бизнес-логика для работы с маршрутом
 * @constructor [BaseViewModel]
 *
 * @param savedStateHandle сущность для сохранения LiveData
 */
class RouteViewModel(
    private val interactor: IRouteInteractor,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel() {

    private var _routes = savedStateHandle.getLiveData<List<TrainStop>>(SAVED_STATE_STOPS)
    val routes: LiveData<List<TrainStop>> = _routes

    /**
     * Метод для получения маршрута определенного поезда
     *
     * @param train поезд для которого нужен маршрут
     */
    fun getRoutes(train: Train) {

        compositeDisposable.add(
            Single.fromCallable {
                interactor.getRouteListRid(train)
            }
                .delay(1, TimeUnit.SECONDS) //сервер сразу не отвечает на rid запрос
                .flatMap {
                    Single.fromCallable {
                        it?.let { rid ->
                            interactor.getRoutesList(rid)
                        }
                    }
                }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally { _progress.value = false }
                .doOnSubscribe { _progress.value = true }
                .subscribe(
                    _routes::setValue
                ) { _error.value = Event(it) }
        )
    }

}