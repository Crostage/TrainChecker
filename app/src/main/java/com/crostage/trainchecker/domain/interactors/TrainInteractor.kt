package com.crostage.trainchecker.domain.interactors

import com.crostage.trainchecker.domain.interactors.interfaces.ITrainInteractor
import com.crostage.trainchecker.domain.model.Train
import com.crostage.trainchecker.domain.network.ITrainService
import com.crostage.trainchecker.domain.repository.ITrainRepository
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Реализация [ITrainInteractor]
 *
 * @property service сервис для получения [Train] данных из сети
 */
class TrainInteractor @Inject constructor(
    private val service: ITrainService,
    private val repository: ITrainRepository,
) : FavouriteInteractor(repository), ITrainInteractor {

    override fun checkFavouritesContainsTrains(
        trains: List<Train>,
        favourite: List<Train>,
    ): List<Train> {
        return trains.map {
            it.isFavourite = favourite.contains(it)
            it
        }
    }

    override fun getTrainList(codeFrom: Int, codeTo: Int, date: String): Single<List<Train>> {
        return Single.fromCallable {
            service.getTrainListRid(codeFrom, codeTo, date)
        }
            .delay(4, TimeUnit.SECONDS) //сервер не успевает обработать запрос
            .flatMap { rid ->
                Single.fromCallable { service.getTrainList(rid) }
                    .flatMap { list ->
                        Single.fromCallable {
                            val favourite = repository.getFavouriteList()
                            list.map {
                                it.isFavourite = favourite.contains(it)
                                it
                            }
                        }
                    }

            }


    }

}