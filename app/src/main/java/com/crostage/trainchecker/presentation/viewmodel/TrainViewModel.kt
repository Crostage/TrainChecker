package com.crostage.trainchecker.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.crostage.trainchecker.domain.interactors.interfaces.ITrainInteractor
import com.crostage.trainchecker.domain.model.Train
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * ViewModel для работы со списками поездов
 *
 * @property trainInteractor бизнес логика получения списка поездов по запросу
 * @property favouriteInteractor бизнес логика получения списка избранного
 */


class TrainViewModel(
    private val trainInteractor: ITrainInteractor,
    savedStateHandle: SavedStateHandle,
) : FavouriteViewModel(trainInteractor, savedStateHandle) {

    /**
     * Получение списка поездов по поисковому запросу
     *
     * @param codeFrom станция отправления
     * @param codeTo станция прибытия
     * @param date дата отправления
     */

    fun trainsFromSearchRequest(codeFrom: Int, codeTo: Int, date: String) {

        compositeDisposable.add(
            trainInteractor.getTrainList(codeFrom, codeTo, date)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally { _progress.value = false }
                .doOnSubscribe { _progress.value = true }
                .subscribe(
                    _trains::setValue,
                    _error::setValue
                )
        )

    }

    /**
     * Добавление поезда в избранное
     *
     * @param train поезд для добавления
     */
    fun insertToFavourite(train: Train) {
        compositeDisposable.add(
            Completable.fromCallable {
                trainInteractor.insertFavourite(train)
            }
                .subscribeOn(Schedulers.computation())
                .subscribe(
                    {},
                    _error::setValue
                )
        )
    }


    /**
     * Проверка списка поездов на наличие отслеживаемых
     *
     * @param favourites список отслеживаемых
     */
    fun checkFavouritesContainsTrains(favourites: List<Train>) {
        val list =
            _trains.value?.let { trainInteractor.checkFavouritesContainsTrains(it, favourites) }
        list?.let(_trains::setValue)
    }


}