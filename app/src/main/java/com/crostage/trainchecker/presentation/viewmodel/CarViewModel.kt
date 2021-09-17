package com.crostage.trainchecker.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import com.crostage.trainchecker.domain.interactors.interfaces.ISeatInteractor
import com.crostage.trainchecker.domain.model.Car
import com.crostage.trainchecker.domain.model.Train
import com.crostage.trainchecker.presentation.model.Event
import com.crostage.trainchecker.utils.Constant.Companion.SAVED_STATE_CARS
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * View Model для экрана вагонов
 *
 * @property interactor бизнес-логки для работы вагонами
 * @constructor [BaseViewModel]
 *
 * @param savedStateHandle сущность для сохранения LiveData
 */
class CarViewModel(
    private val interactor: ISeatInteractor,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel() {
    private var _cars = savedStateHandle.getLiveData<List<Car>>(SAVED_STATE_CARS)
    val cars: LiveData<List<Car>> = _cars

    /**
     * Метод для получения списка вагонов определенного поезда
     *
     * @param train поезд для которого нужен список вагонов
     */
    fun getCarList(train: Train) {

        compositeDisposable.add(
            Single.fromCallable {
                interactor.getCarsRid(train)
            }
                .delay(1, TimeUnit.SECONDS)
                .flatMap { rid ->
                    Single.fromCallable {
                        rid?.let { interactor.getCars(it) }
                    }
                }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally { _progress.value = false }
                .doOnSubscribe { _progress.value = true }
                .subscribe(
                    _cars::setValue
                ) { _error.value = Event(it) }
        )
    }

}