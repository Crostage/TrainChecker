package com.crostage.trainchecker.utils

import com.crostage.trainchecker.data.model.rout.RoutesResult
import com.crostage.trainchecker.data.model.seat.SeatResult
import com.crostage.trainchecker.data.model.train.SearchResult
import com.crostage.trainchecker.data.network.ApiRequests
import com.crostage.trainchecker.utils.Helper.Companion.executeAndExceptionChek

class NetworkUtil {
    companion object {

        fun <T> getResponseFromId(rid: Long, retrofitApi: ApiRequests, clazz: Class<T>): T? {

            var data: T? = null

            Thread.sleep(2000) // сервер сразу не успевает обработать второй запрос

            val response = when {

                clazz.isAssignableFrom(SearchResult::class.java) ->
                    retrofitApi.getResultFromSearchRid(
                        requestId = rid
                    ).executeAndExceptionChek()

                clazz.isAssignableFrom(RoutesResult::class.java) ->
                    retrofitApi.getResultFromRoutesRid(
                        requestId = rid
                    ).executeAndExceptionChek()

                clazz.isAssignableFrom(SeatResult::class.java) ->
                    retrofitApi.getResultFromSeatRid(
                        requestId = rid
                    ).executeAndExceptionChek()

                else -> throw  IllegalArgumentException("Unknown Rid class")

            }

            response?.let {
                if (response.isSuccessful) {
                    data = response.body() as T
                }
            }

            return data
        }
    }
}