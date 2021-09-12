package com.crostage.trainchecker.data.network.util

import com.crostage.trainchecker.data.model.GeneralResult
import com.crostage.trainchecker.data.network.ApiRequests
import com.crostage.trainchecker.utils.Error401
import com.crostage.trainchecker.utils.Error404
import com.crostage.trainchecker.utils.ErrorConnections
import com.crostage.trainchecker.utils.ServerSendError
import retrofit2.Call
import retrofit2.Response

class NetworkUtil {
    companion object {
        fun getResponseFromId(layerId: Int, rid: Long, retrofitApi: ApiRequests): GeneralResult? {

            var data: GeneralResult? = null

            val response =
                retrofitApi.getResultFromRid(
                    layerId = layerId,
                    requestId = rid
                ).executeAndExceptionChek()


            response?.let {
                if (response.isSuccessful) {
                    data = response.body() as GeneralResult
                }
            }

            return data
        }

        fun <T> Call<T>.executeAndExceptionChek(): Response<T>? {
            try {
                val response = execute()
                when (response.code()) {

                    200 -> return response
                    401 -> throw Error401()
                    404 -> throw Error404()

                }
            } catch (e: ServerSendError) {
                throw e
            } catch (e: Exception) {
                throw ErrorConnections()
            }
            return null
        }
    }
}