package com.crostage.trainchecker.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.crostage.trainchecker.domain.interactors.interfaces.IFavouriteInteractor
import com.crostage.trainchecker.domain.model.Train
import com.crostage.trainchecker.presentation.model.Event
import com.crostage.trainchecker.presentation.util.Helper
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * View Model экрана отслеживаемых поездов
 *
 * @property interactor бизнес-логика для отслеживания
 */
open class FavouriteViewModel(
    private val interactor: IFavouriteInteractor,
) : BaseViewModel() {

    private val _openDetail = MutableLiveData<Event<Train>>()
    val openDetail: LiveData<Event<Train>> = _openDetail


    /**
     * Удаление поезда из избранного
     *
     * @param train поезд для удаления
     */
    fun removeFromFavourite(train: Train) {
        compositeDisposable.add(
            Completable.fromCallable {
                interactor.removeFavourite(train)
            }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { },
                    { _error.value = Event(it) }
                )
        )
    }


    /**
     * Обработка клика по поезду
     *
     * @param train поезд по которому нажали
     */
    fun trainClicked(train: Train) {
        _openDetail.value = Event(train)
    }


    /**
     * Получение списка отслеживаемых
     *
     */
    fun getFavouriteLiveData() =
        interactor.getFavouriteLiveData()


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

}