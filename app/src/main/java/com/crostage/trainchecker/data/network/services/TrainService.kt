package com.crostage.trainchecker.data.network.services

import com.crostage.trainchecker.data.network.RetrofitBuilder
import com.crostage.trainchecker.domain.network.ITrainService
import com.crostage.trainchecker.model.data.BaseRequest
import com.crostage.trainchecker.model.data.train.SearchResult
import com.crostage.trainchecker.model.data.train.Train
import com.crostage.trainchecker.utils.Constant
import com.crostage.trainchecker.utils.Helper.Companion.executeAndExceptionChek

class TrainService : ITrainService {

    override fun getTrainList(codeFrom: Int, codeTo: Int, date: String): List<Train> {

        val retrofitApi = RetrofitBuilder.getApi
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
        val retrofitApi = RetrofitBuilder.getApi

        var data: SearchResult? = null

        //отправка запроса с rid
        val responseRid = retrofitApi.getResultFromSearchRid(
                layerId = Constant.TRAIN_LAYER_ID, requestId = rid
        ).executeAndExceptionChek()

        responseRid?.let {
            if (it.isSuccessful) {
                data = it.body() as SearchResult
                val responseResult = data?.result

                if (responseResult == "RID") {
                    //сервер сразу не обрабатывает запросы, пришлось воткнуть задержку
                    Thread.sleep(2000)

                    //еще одна отправка запроса с rid
                    val responseTrains = retrofitApi.getResultFromSearchRid(
                            layerId = Constant.TRAIN_LAYER_ID, requestId = rid
                    ).executeAndExceptionChek()

                    responseTrains?.let {

                        if (responseTrains.isSuccessful) {
                            data = responseTrains.body() as SearchResult
                        }
                    }
                }
            }
        }
        return data
    }


}