package com.crostage.trainchecker.data.network.services

import com.crostage.trainchecker.data.network.ApiRequests
import com.crostage.trainchecker.domain.network.ITrainService
import com.crostage.trainchecker.model.data.BaseResult
import com.crostage.trainchecker.model.data.train.SearchResult
import com.crostage.trainchecker.model.data.train.Train
import com.crostage.trainchecker.utils.Constant
import com.crostage.trainchecker.utils.Helper.Companion.executeAndExceptionChek
import javax.inject.Inject

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
                val data = getResponseFromId<SearchResult>(rid)

                val l = data?.tp?.get(0)?.list
                l?.let { return l }

            }
        }
        return mutableListOf()
    }

    private fun <T> getResponseFromId(rid: Long): T? {
        var data: T? = null

        Thread.sleep(1000)

        val responseTrains = retrofitApi.getResultFromSearchRid(requestId = rid
        ).executeAndExceptionChek()

        responseTrains?.let {
            if (responseTrains.isSuccessful) {
                data = responseTrains.body() as T
            }
        }

        return data
    }


}