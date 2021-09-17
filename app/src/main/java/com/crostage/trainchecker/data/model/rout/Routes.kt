package com.crostage.trainchecker.data.model.rout

import com.google.gson.annotations.SerializedName

/**
 * Модель маршутов
 *
 * @property routList список остановок [TrainStopDto]
 */
data class Routes(
    @SerializedName("Stop")
    val routList: List<TrainStopDto>,
)

/**
 * Модель ответа от сервера по маршрутам
 *
 * @property routes список остановок [TrainStopDto]
 * @property error ошибка [RoutesError]
 */
data class Response(
    @SerializedName("Routes")
    val routes: List<TrainStopDto>,
    @SerializedName("Error")
    val error: RoutesError?,
)

/**
 * Модель ошибки по запросу маршрутов
 *
 * @property content сообщение ошибки
 */
data class RoutesError(
    val content: String,
)

