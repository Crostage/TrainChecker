package com.crostage.trainchecker.data.model.rid

import com.google.gson.annotations.SerializedName

/**
 * Модель для получения id запроса маршуртов из сети
 *
 * @property requestId id запроса маршуртов
 */
data class RouteRidResult(
    @SerializedName("rid")
    val requestId: Long,
)