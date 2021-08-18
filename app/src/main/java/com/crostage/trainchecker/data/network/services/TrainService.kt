package com.crostage.trainchecker.data.network.services

import com.crostage.trainchecker.data.network.ApiRequests
import com.crostage.trainchecker.domain.network.ITrainService
import com.crostage.trainchecker.model.data.BaseRequest
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

                val body = it.body() as BaseRequest
                val rid = body.requestId
                val data = getResponseFromId(rid)

                val l = data?.tp?.get(0)?.list
                l?.let { return l }

            }
        }
        return mutableListOf()
    }

    private fun getResponseFromId(rid: Long): SearchResult? {
        var data: SearchResult? = null

        Thread.sleep(1000)

        //еще одна отправка запроса с rid
        val responseTrains = retrofitApi.getResultFromSearchRid(requestId = rid
        ).executeAndExceptionChek()

        responseTrains?.let {
            if (responseTrains.isSuccessful) {
                data = responseTrains.body() as SearchResult
            }
        }

        return data
    }


}