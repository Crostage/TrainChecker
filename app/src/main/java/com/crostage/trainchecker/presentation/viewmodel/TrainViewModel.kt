package com.crostage.trainchecker.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.crostage.trainchecker.domain.interactors.interfaces.ITrainInteractor
import com.crostage.trainchecker.model.domain.Train
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class TrainViewModel(
    private val trainInteractor: ITrainInteractor,
) : ViewModel() {

    private var _trains = MutableLiveData<List<Train>>()
    val trains: LiveData<List<Train>> = _trains

    private var _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    private var _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean> = _progress

    private val compositeDisposable = CompositeDisposable()

    fun trainsFromSearchRequest(codeFrom: Int, codeTo: Int, date: String) {

        compositeDisposable.add(
            Single.fromCallable {

                trainInteractor.getTrainList(codeFrom, codeTo, date)
            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally { _progress.value = false }
                .doOnSubscribe { _progress.value = true }
                .subscribe(
                    _trains::setValue,
                    _error::setValue
                )
        )
    }

    fun removeFromFavourite(train: Train) {
        compositeDisposable.add(
            Single.fromCallable {
                trainInteractor.removeTrain(train)
            }
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { },
                    _error::setValue
                )
        )
    }


    fun checkFavouritesContainsTrains(favourites: List<Train>) {
        val list =
            _trains.value?.let { trainInteractor.checkFavouritesContainsTrains(it, favourites) }
        list?.let(_trains::setValue)
    }

    fun insertToFavourite(train: Train) {
        compositeDisposable.add(
            Single.fromCallable {
                trainInteractor.insertTrain(train)
            }
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { },
                    _error::setValue
                )
        )
    }

    fun getFavouriteTrainList(): LiveData<List<Train>> {
        return trainInteractor.getFavouriteLiveData()
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}