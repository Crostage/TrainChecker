package com.crostage.trainchecker.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseRequest(
    val result: String,
    @SerializedName("RID")
    @Expose
    val requestId: Long
)