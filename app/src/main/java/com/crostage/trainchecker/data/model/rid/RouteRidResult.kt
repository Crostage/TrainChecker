package com.crostage.trainchecker.data.model.rid

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RouteRidResult(
    val type: String,
    @SerializedName("rid")
    @Expose
    val requestId: Long,
)