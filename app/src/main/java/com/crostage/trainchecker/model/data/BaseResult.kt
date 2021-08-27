package com.crostage.trainchecker.model.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseResult(
    val result: String,
    @SerializedName("RID")
    @Expose
    val requestId: Long,
)