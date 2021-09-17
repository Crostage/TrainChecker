package com.crostage.trainchecker.domain.interactors.interfaces

import com.crostage.trainchecker.domain.model.Train
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

/**
 * Интерактор для работы со поездами
 *
 */
interface ITrainInteractor : IFavouriteInteractor {

    /**
     * Получение поискового запроса по поездам
     *
     * @param codeFrom код станции отправления
     * @param codeTo код станции прибытия
     * @param date дата отправления
     * @return список [Train]
     */
    fun getTrainList(codeFrom: Int, codeTo: Int, date: String): Single<List<Train>>

    /**
     * Получение списка отслеживаемых поездов
     *
     * @return список [Train]
     */
    fun getFavouriteObservable(): Observable<List<Train>>
}
