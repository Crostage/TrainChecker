package com.crostage.trainchecker.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.crostage.trainchecker.domain.interactors.FavouriteInteractor
import com.crostage.trainchecker.model.data.train.Train
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class FavouriteViewModel(
    private val favouriteInteractor: FavouriteInteractor,
) : ViewModel() {

    private var _trains = MutableLiveData<List<Train>>()
    val trains: LiveData<List<Train>> = _trains

    private var _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    private var _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean> = _progress

    private val compositeDisposable = CompositeDisposable()

    fun getFavouriteList(): LiveData<List<Train>> {
//
//        compositeDisposable.add(
//            Single.fromCallable {
        return favouriteInteractor.getTrainList()
//            }
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doFinally { _progress.value = false }
//                .doOnSubscribe { _progress.value = true }
//                .subscribe({
//
//                }        )
    }

    fun removeFromFavourite(train: Train) {
        compositeDisposable.add(
            Single.fromCallable {
                favouriteInteractor.removeTrain(train)
                _trains.value?.toMutableList()?.remove(train)
            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally { _progress.value = false }
                .doOnSubscribe { _progress.value = true }
                .subscribe(
                    { },
                    _error::setValue
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}