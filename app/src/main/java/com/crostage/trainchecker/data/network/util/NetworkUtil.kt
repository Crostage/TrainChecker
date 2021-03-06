package com.crostage.trainchecker.data.network.util

import com.crostage.trainchecker.data.model.GeneralResult
import com.crostage.trainchecker.data.network.ApiRequests
import com.crostage.trainchecker.utils.Error401
import com.crostage.trainchecker.utils.Error404
import com.crostage.trainchecker.utils.ErrorConnections
import com.crostage.trainchecker.utils.ServerSendError
import retrofit2.Call
import retrofit2.Response

/**
 * Вспомогательный класс для работы с сетью
 *
 */
class NetworkUtil {
    companion object {
        /**
         * Получить результат запроса по ID
         *
         * @param layerId подкатегория запроса
         * @param rid идентификатор запроса
         * @param retrofitApi класс для работы сетью
         * @return общий результат по данному запросу [GeneralResult]
         */
        fun getResponseFromId(layerId: Int, rid: Long, retrofitApi: ApiRequests): GeneralResult? {

            var data: GeneralResult? = null

            val response =
                retrofitApi.getResultFromRid(
                    layerId = layerId,
                    requestId = rid
                ).executeAndExceptionChek()

            if (response.isSuccessful) {
                data = response.body() as GeneralResult
            }

            return data
        }

        /**
         * Функция запуска и проверки запроса на ошибки
         *
         * @param T тип запроса
         * @return ответ от сервера запрашиваемого типа типа
         */
        fun <T> Call<T>.executeAndExceptionChek(): Response<T> {
            try {
                val response = execute()
                when (response.code()) {

                    ServerCodeEnum.OK.code -> return response
                    ServerCodeEnum.NOT_FOUND.code -> throw Error401()
                    ServerCodeEnum.NO_AUTH.code -> throw Error404()
                    else -> throw ErrorConnections()

                }
            } catch (e: ServerSendError) {
                throw e
            } catch (e: Error401) {
                throw e
            } catch (e: Error404) {
                throw e
            } catch (e: Exception) {
                throw ErrorConnections()
            }
        }
    }
}