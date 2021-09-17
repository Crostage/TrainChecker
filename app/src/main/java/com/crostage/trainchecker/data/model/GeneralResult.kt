package com.crostage.trainchecker.data.model

import com.crostage.trainchecker.data.model.rout.Response
import com.crostage.trainchecker.data.model.seat.CarResponse
import com.crostage.trainchecker.data.model.train.TrainResponse
import com.google.gson.annotations.SerializedName

/**
 * Модель общего ответа от сервера
 *
 * @property carResponse вагоны
 * @property routeResponse маршруты
 * @property trainResponse поезда
 * @property requestId id запроса
 */
class GeneralResult(
    @SerializedName("lst")
    val carResponse: List<CarResponse>?,
    @SerializedName("GtExpress_Response")
    val routeResponse: Response?,
    @SerializedName("tp")
    val trainResponse: List<TrainResponse>?,
    @SerializedName("RID")
    val requestId: Long?,
)