package com.crostage.trainchecker.model.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseRoutesRequest(
    val type: String,
    @SerializedName("rid")
    @Expose
    val requestId: Long
)