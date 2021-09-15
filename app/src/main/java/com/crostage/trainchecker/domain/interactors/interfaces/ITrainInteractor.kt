package com.crostage.trainchecker.domain.interactors.interfaces

import com.crostage.trainchecker.domain.model.Train
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface ITrainInteractor : IFavouriteInteractor {

    /**
     * Получение request id поискового запроса
     *
     * @param codeFrom код станции отправления
     * @param codeTo код станции прибытия
     * @param date дата отправления
     * @return наблюдаемый список [Train]
     */

    fun getTrainList(codeFrom: Int, codeTo: Int, date: String): Single<List<Train>>

    fun getFavouriteObservable(): Observable<List<Train>>
}
