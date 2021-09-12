package com.crostage.trainchecker.data.model.rid

import com.google.gson.annotations.SerializedName

data class RouteRidResult(
    @SerializedName("rid")
    val requestId: Long,
)