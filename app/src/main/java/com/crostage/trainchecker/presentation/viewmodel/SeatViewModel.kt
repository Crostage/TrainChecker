package com.crostage.trainchecker.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.crostage.trainchecker.domain.interactors.interfaces.ISeatInteractor
import com.crostage.trainchecker.data.model.seat.Car
import com.crostage.trainchecker.data.model.train.Train
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class SeatViewModel(
    private val interactor: ISeatInteractor,
) : ViewModel() {
    private var _cars = MutableLiveData<List<Car>>()
    val cars: LiveData<List<Car>> = _cars

    private var _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    private var _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean> = _progress


    private val compositeDisposable = CompositeDisposable()

    fun getCarList(train: Train) {

        compositeDisposable.add(
            Single.fromCallable {
                interactor.getSeats(train)
            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally { _progress.value = false }
                .doOnSubscribe { _progress.value = true }
                .subscribe(
                    _cars::setValue,
                    _error::setValue
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}