package com.crostage.trainchecker.presentation.viewmodel

import com.crostage.trainchecker.domain.interactors.interfaces.IFavouriteInteractor
import com.crostage.trainchecker.domain.interactors.interfaces.ITrainInteractor
import com.crostage.trainchecker.domain.model.Train
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * ViewModel для работы со списками поездов
 *
 * @property trainInteractor бизнес логика получения списка поездов по запросу
 * @property favouriteInteractor бизнес логика получения списка избранного
 */


open class TrainViewModel(
    private val trainInteractor: ITrainInteractor,
    private val favouriteInteractor: IFavouriteInteractor,
) : FavouriteViewModel(favouriteInteractor) {

    /**
     * Получение списка поездов по поисковому запросу
     *
     * @param codeFrom станция отправления
     * @param codeTo станция прибытия
     * @param date дата отправления
     */

    fun trainsFromSearchRequest(codeFrom: Int, codeTo: Int, date: String) {

        compositeDisposable.add(

            Single
                .fromCallable {
                    trainInteractor.getTrainListRid(codeFrom, codeTo, date)
                }
                .delay(1, TimeUnit.SECONDS)
                .flatMap { rid ->
                    Single.fromCallable {
                        rid?.let { trainInteractor.getTrainList(it) }
                    }
                }

                //todo:проверка, содержится ли поезд в избранном
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

    /**
     * Добавление поезда в избранное
     *
     * @param train поезд для добавления
     */
    fun insertToFavourite(train: Train) {
        compositeDisposable.add(
            Completable.fromCallable {
                favouriteInteractor.insertTrain(train)
            }
                .subscribeOn(Schedulers.io())
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