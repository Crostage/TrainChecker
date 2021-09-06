package com.crostage.trainchecker.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.crostage.trainchecker.domain.interactors.interfaces.IFavouriteInteractor
import com.crostage.trainchecker.domain.interactors.interfaces.ITrainInteractor
import com.crostage.trainchecker.domain.model.Train
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class TrainViewModel(
    private val trainInteractor: ITrainInteractor,
    private val favouriteInteractor: IFavouriteInteractor,
) : ViewModel() {

    private var _openDetail = MutableLiveData<Event<Train>>()
    val openDetail: LiveData<Event<Train>> = _openDetail

    private var _trains = MutableLiveData<List<Train>>()
    val trains: LiveData<List<Train>> = _trains

    private var _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    private var _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean> = _progress

    private val compositeDisposable = CompositeDisposable()

    fun trainsFromSearchRequest(codeFrom: Int, codeTo: Int, date: String) {

        compositeDisposable.add(

            Single
                .fromCallable {
                    trainInteractor.getTrainListRid(codeFrom, codeTo, date)
                }
                .delay(3, TimeUnit.SECONDS)
                .flatMap { rid ->
                    Single.fromCallable {
                        rid?.let { trainInteractor.getTrainList(it) }
                    }
                }

                //todo костыль опять
                .map { list ->
                    val m = favouriteInteractor.getFavouriteList()
                    list.map { train ->
                        train.isFavourite = m.contains(train)
                        train
                    }
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
            Completable.fromCallable {
                favouriteInteractor.removeTrain(train)
            }
                .subscribeOn(Schedulers.io())
                .onErrorReturn { }
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
            Completable.fromCallable {
                favouriteInteractor.insertTrain(train)
            }
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { },
                    _error::setValue
                )
        )
    }

    fun trainClicked(train: Train) {
        _openDetail.value = Event(train)
    }

    fun getFavouriteTrainList() =
        favouriteInteractor.getFavouriteLiveData()


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}