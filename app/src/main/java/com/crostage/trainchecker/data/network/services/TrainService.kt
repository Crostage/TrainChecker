package com.crostage.trainchecker.data.network.services

import com.crostage.trainchecker.data.model.BaseResult
import com.crostage.trainchecker.data.model.seat.SeatResult
import com.crostage.trainchecker.data.model.train.SearchResult
import com.crostage.trainchecker.data.model.train.Train
import com.crostage.trainchecker.data.network.ApiRequests
import com.crostage.trainchecker.domain.network.ITrainService
import com.crostage.trainchecker.utils.Constant
import com.crostage.trainchecker.utils.Helper.Companion.executeAndExceptionChek
import com.crostage.trainchecker.utils.NetworkUtil.Companion.getResponseFromId
import javax.inject.Inject

/**
 *
 * Реализация [ITrainService]
 *
 * @property retrofitApi класс для работы с сетью
 */

class TrainService @Inject constructor(private val retrofitApi: ApiRequests) : ITrainService {

    override fun getTrainList(codeFrom: Int, codeTo: Int, date: String): List<Train> {

        // запрос для получения requestId
        val responseRid = retrofitApi.getTrains(
            layerId = Constant.TRAIN_LAYER_ID,
            codeFrom = codeFrom,
            codeTo = codeTo,
            date = date
        ).executeAndExceptionChek()

        responseRid?.let {

            if (it.isSuccessful) {

                val body = it.body() as BaseResult
                val rid = body.requestId
                val data = getResponseFromId(rid, retrofitApi, SearchResult::class.java)

                val l = data?.tp?.get(0)?.list
                l?.let { return l }

            }
        }
        return mutableListOf()
    }


}