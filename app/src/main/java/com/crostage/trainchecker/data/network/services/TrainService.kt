package com.crostage.trainchecker.data.network.services

import com.crostage.trainchecker.data.model.GeneralResult
import com.crostage.trainchecker.data.model.train.TrainEntity
import com.crostage.trainchecker.data.network.ApiRequests
import com.crostage.trainchecker.data.network.util.NetworkUtil.Companion.executeAndExceptionChek
import com.crostage.trainchecker.data.network.util.NetworkUtil.Companion.getResponseFromId
import com.crostage.trainchecker.domain.converter.IConverter
import com.crostage.trainchecker.domain.model.Train
import com.crostage.trainchecker.domain.network.ITrainService
import com.crostage.trainchecker.utils.Constant.Companion.TRAIN_LAYER_ID
import com.crostage.trainchecker.utils.ServerSendError
import java.lang.IndexOutOfBoundsException
import javax.inject.Inject

/**
 *
 * Реализация [ITrainService]
 *
 * @property retrofitApi класс для работы с сетью
 */

class TrainService @Inject constructor(
    private val retrofitApi: ApiRequests,
    private val converter: IConverter<List<TrainEntity>, List<Train>>,
) : ITrainService {

    override fun getTrainListRid(codeFrom: Int, codeTo: Int, date: String): Long? {

        var rid: Long? = null

        val responseRid = retrofitApi.getTrains(
            codeFrom = codeFrom,
            codeTo = codeTo,
            date = date
        ).executeAndExceptionChek()

        responseRid.let {

            try {
                if (it.isSuccessful) {
                    val body = it.body() as GeneralResult

                    val errorMessage = body.listResponse?.get(0)?.msgList?.get(0)?.message
                    if (errorMessage != null) throw ServerSendError(errorMessage)


                    rid = body.requestId
                }
            } catch (e: IndexOutOfBoundsException) {
                throw ServerSendError()
            }
        }
        return rid
    }

    override fun getTrainList(rid: Long?): List<Train> {
        var trainList: List<Train> = mutableListOf()
        if (rid == null) return trainList

        val data = getResponseFromId(TRAIN_LAYER_ID, rid, retrofitApi)

        try {
            val l = data?.listResponse?.get(0)?.list
            l?.let { trainList = converter.convert(l) }
        } catch (e: IndexOutOfBoundsException) {
            throw ServerSendError()
        }

        return trainList
    }


}