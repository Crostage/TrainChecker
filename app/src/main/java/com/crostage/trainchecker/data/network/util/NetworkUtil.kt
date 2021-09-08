package com.crostage.trainchecker.data.network.util

import com.crostage.trainchecker.data.model.rout.RoutesResult
import com.crostage.trainchecker.data.model.seat.SeatResult
import com.crostage.trainchecker.data.model.train.SearchResult
import com.crostage.trainchecker.data.network.ApiRequests
import com.crostage.trainchecker.utils.Error401
import com.crostage.trainchecker.utils.Error404
import com.crostage.trainchecker.utils.ErrorConnections
import com.crostage.trainchecker.utils.ServerSendError
import retrofit2.Call
import retrofit2.Response

class NetworkUtil {
    companion object {
        @Suppress("UNCHECKED_CAST")
        fun <T> getResponseFromId(rid: Long, retrofitApi: ApiRequests, clazz: Class<T>): T? {

            var data: T? = null

            val response = when {

                clazz.isAssignableFrom(SearchResult::class.java) -> {
                    retrofitApi.getResultFromSearchRid(
                        requestId = rid
                    ).executeAndExceptionChek()
                }

                clazz.isAssignableFrom(RoutesResult::class.java) -> {
                    retrofitApi.getResultFromRoutesRid(
                        requestId = rid
                    ).executeAndExceptionChek()
                }
                clazz.isAssignableFrom(SeatResult::class.java) -> {
                    retrofitApi.getResultFromSeatRid(
                        requestId = rid
                    ).executeAndExceptionChek()
                }
                else -> throw  IllegalArgumentException("Unknown Rid class")

            }

            response?.let {
                if (response.isSuccessful) {
                    data = response.body() as T
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