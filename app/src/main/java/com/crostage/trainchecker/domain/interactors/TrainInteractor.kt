package com.crostage.trainchecker.domain.interactors

import com.crostage.trainchecker.domain.interactors.interfaces.ITrainInteractor
import com.crostage.trainchecker.domain.model.Train
import com.crostage.trainchecker.domain.network.ITrainService
import com.crostage.trainchecker.domain.repository.ITrainRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Реализация [ITrainInteractor]
 *
 * @property service сервис для получения [Train] данных из сети
 * @property repository сервис для получения отслеживаемых [Train] из БД
 */
class TrainInteractor @Inject constructor(
    private val service: ITrainService,
    private val repository: ITrainRepository,
) : FavouriteInteractor(repository), ITrainInteractor {

    /**
     * @see ITrainInteractor.getFavouriteObservable
     */
    override fun getFavouriteObservable(): Observable<List<Train>> {
        return repository.getFavouriteObservable()
    }

    /**
     * @see ITrainInteractor.getTrainList
     */
    override fun getTrainList(codeFrom: Int, codeTo: Int, date: String): Single<List<Train>> {
        return Single.fromCallable {
            service.getTrainListRid(codeFrom, codeTo, date)
        }
            .delay(4, TimeUnit.SECONDS) //сервер не успевает обработать запрос
            .flatMap { rid ->
                Single.fromCallable { service.getTrainList(rid) }
            }
    }

}