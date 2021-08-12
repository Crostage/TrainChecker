package com.crostage.trainchecker.data.network.services

import com.crostage.trainchecker.data.network.RetrofitBuilder
import com.crostage.trainchecker.model.BaseRequest
import com.crostage.trainchecker.model.train.SearchResult
import com.crostage.trainchecker.model.train.Train
import com.crostage.trainchecker.utils.Constant
import com.crostage.trainchecker.utils.Helper.Companion.executeAndExceptionChek

class TrainService : ITrainService {

    override fun getTrainList(codeFrom: Int, codeTo: Int, date: String): List<Train> {

     val retrofitApi = RetrofitBuilder.getApi
        val response = retrofitApi.getTrains(
            layerId = Constant.TRAIN_LAYER_ID, codeFrom = codeFrom, codeTo = codeTo, date = date
        ).executeAndExceptionChek()

        response?.let {

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
        val retrofitApi = RetrofitBuilder.getApi

        var data: SearchResult? = null

        val response1 = retrofitApi.getResultFromSearchRid(
            layerId = Constant.TRAIN_LAYER_ID, requestId = rid
        ).executeAndExceptionChek()

        response1?.let {
            if (it.isSuccessful) {
                data = it.body() as SearchResult
                val responseResult = data?.result

                if (responseResult == "RID") {
                    //сервер сразу не обрабатывает запросы, пришлось воткнуть задержку
                    Thread.sleep(2000)

                    val response2 = retrofitApi.getResultFromSearchRid(
                        layerId = Constant.TRAIN_LAYER_ID, requestId = rid
                    ).executeAndExceptionChek()

                    response2?.let {

                        if (response2.isSuccessful) {
                            data = response2.body() as SearchResult
                        }
                    }
                }
            }
        }
        return data
    }


}