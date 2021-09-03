package com.crostage.trainchecker.data.model.rid

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseRidResult(
    val result: String,
    @SerializedName("RID")
    @Expose
    val requestId: Long,
)