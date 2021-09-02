package com.crostage.trainchecker.data.network.services

import android.util.Log
import com.crostage.trainchecker.data.converter.IConverter
import com.crostage.trainchecker.data.network.ApiRequests
import com.crostage.trainchecker.data.network.NetworkUtil.Companion.getResponseFromId
import com.crostage.trainchecker.domain.network.ITrainService
import com.crostage.trainchecker.model.data.BaseResult
import com.crostage.trainchecker.model.data.train.SearchResult
import com.crostage.trainchecker.model.data.train.TrainEntity
import com.crostage.trainchecker.model.domain.Train
import com.crostage.trainchecker.utils.Constant
import com.crostage.trainchecker.utils.Helper.Companion.executeAndExceptionChek
import javax.inject.Inject

/**
 *
 * Реализация [ITrainService]
 *
 * @property retrofitApi класс для работы с сетью
 */

class TrainService @Inject constructor(
    private val retrofitApi: ApiRequests,
    private val converter: IConverter<TrainEntity, Train>,
) : ITrainService {

    companion object {
        private const val TAG = "TrainService"
    }

    override fun getTrainListRid(codeFrom: Int, codeTo: Int, date: String): Long? {

        var rid: Long? = null
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
                rid = body.requestId


            }
        }
        return rid
    }

    override fun getTrainList(rid: Long): List<Train> {
        var trainList: List<Train> = mutableListOf()
        val data = getResponseFromId(rid, retrofitApi, SearchResult::class.java)

        val l = data?.tp?.get(0)?.list
        l?.let { trainList = l.map { entity -> converter.convert(entity) } }
        return trainList
    }


}