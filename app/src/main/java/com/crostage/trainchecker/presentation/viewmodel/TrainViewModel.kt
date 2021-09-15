package com.crostage.trainchecker.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import com.crostage.trainchecker.domain.interactors.interfaces.ITrainInteractor
import com.crostage.trainchecker.domain.model.Train
import com.crostage.trainchecker.utils.Constant.Companion.SAVED_STATE_TRAINS
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * ViewModel для работы со списками поездов
 *
 * @property interactor бизнес логика получения списка поездов по запросу
 * @property favouriteInteractor бизнес логика получения списка избранного
 */


class TrainViewModel(
    private val interactor: ITrainInteractor,
    savedStateHandle: SavedStateHandle,
) : FavouriteViewModel(interactor) {


    private val _trains = savedStateHandle.getLiveData<List<Train>>(SAVED_STATE_TRAINS)
    val trains: LiveData<List<Train>> = _trains

    /**
     * Получение списка поездов по поисковому запросу
     *
     * @param codeFrom станция отправления
     * @param codeTo станция прибытия
     * @param date дата отправления
     */

    fun trainsFromSearchRequest(codeFrom: Int, codeTo: Int, date: String) {

        compositeDisposable.add(
            interactor.getTrainList(codeFrom, codeTo, date)
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
                interactor.insertFavourite(train)
            }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
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
    fun checkFavouritesContainsTrains(favourites: List<Train>, trains: List<Train>): List<Train>? {
        return trains.let {
            interactor.checkFavouritesContainsTrains(it, favourites)
        }
    }


}