package com.crostage.trainchecker.data.model.rid

import com.google.gson.annotations.SerializedName

/**
 * Модель для получения id запроса свободных мест из сети
 *
 * @property requestId id запроса свободных мест
 */
data class SeatRidResult(
    @SerializedName("RID")
    val requestId: Long,
)