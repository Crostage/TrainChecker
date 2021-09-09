package com.crostage.trainchecker.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.crostage.trainchecker.domain.interactors.interfaces.IFavouriteInteractor
import com.crostage.trainchecker.domain.model.Train
import com.crostage.trainchecker.presentation.util.Helper
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

open class FavouriteViewModel(
    private val favouriteInteractor: IFavouriteInteractor,
) : ViewModel() {

    private val _openDetail = MutableLiveData<Event<Train>>()
    val openDetail: LiveData<Event<Train>> = _openDetail

    protected val _trains = MutableLiveData<List<Train>>()
    val trains: LiveData<List<Train>> = _trains

    protected val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    protected val _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean> = _progress

    protected val compositeDisposable = CompositeDisposable()


    /**
     * Удаление поезда из избранного
     *
     * @param train поезд для удаления
     */
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


    /**
     * Обработка клика по поезду
     *
     * @param train
     */
    fun trainClicked(train: Train) {
        _openDetail.value = Event(train)
    }


    /**
     * Получение списка отслеживаемых
     *
     */
    fun getFavouriteLiveData() =
        favouriteInteractor.getFavouriteLiveData()


    /**
     * Проверка списка отслеживаемых на актуальность даты и времени отправления
     *
     * @param favourites список отслеживаемых поездов
     * @return актуальный список [Train]
     */
    fun chekFavouritesOnActualDate(favourites: List<Train>): List<Train> {
        return favourites.let {
            val actualList =
                Helper.checkFavouritesOnActualDate(it)
            val favouriteList = it.toMutableList()
            favouriteList.removeAll(actualList)
            favouriteList.forEach { train ->
                removeFromFavourite(train)
            }
            actualList
        }
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}