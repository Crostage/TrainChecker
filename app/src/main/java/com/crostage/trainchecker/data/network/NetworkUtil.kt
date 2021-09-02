package com.crostage.trainchecker.data.network

import com.crostage.trainchecker.model.data.rout.RoutesResult
import com.crostage.trainchecker.model.data.seat.SeatResult
import com.crostage.trainchecker.model.data.train.SearchResult
import com.crostage.trainchecker.utils.Helper.Companion.executeAndExceptionChek

class NetworkUtil {
    companion object {

        fun <T> getResponseFromId(rid: Long, retrofitApi: ApiRequests, clazz: Class<T>): T? {

            var data: T? = null

            // сервер сразу не успевает обработать второй запрос

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
    }
}