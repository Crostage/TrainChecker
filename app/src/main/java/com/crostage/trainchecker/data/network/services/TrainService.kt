package com.crostage.trainchecker.data.network.services

import android.util.Log
import com.crostage.trainchecker.model.data.BaseResult
import com.crostage.trainchecker.model.data.train.SearchResult
import com.crostage.trainchecker.model.data.train.TrainEntity
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

    companion object {
        private const val TAG = "TrainService"
    }

    override fun getTrainList(codeFrom: Int, codeTo: Int, date: String): List<TrainEntity> {

        // запрос для получения requestId
        val responseRid = retrofitApi.getTrains(
            layerId = Constant.TRAIN_LAYER_ID,
            codeFrom = codeFrom,
            codeTo = codeTo,
            date = date
        ).executeAndExceptionChek()

        responseRid?.let {
            Log.d(TAG, "TRAIN")
            Log.d(TAG, "${responseRid.raw()}")
            Log.d(TAG, "${responseRid.body()}")
            Log.d(TAG, responseRid.message())

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