package com.crostage.trainchecker.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.crostage.trainchecker.domain.interactors.interfaces.IRouteInteractor
import com.crostage.trainchecker.domain.model.Train
import com.crostage.trainchecker.domain.model.TrainStop
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class RouteViewModel(
    private val interactor: IRouteInteractor,
) : BaseViewModel() {

    private var _routes = MutableLiveData<List<TrainStop>>()
    val routes: LiveData<List<TrainStop>> = _routes

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
                    _routes::setValue,
                    _error::setValue
                )
        )
    }

}