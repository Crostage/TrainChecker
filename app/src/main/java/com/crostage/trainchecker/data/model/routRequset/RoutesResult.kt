package com.crostage.trainchecker.data.model.routRequset

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RoutesResult(
    @SerializedName("GtExpress_Response")
    @Expose
    val response: Test
)