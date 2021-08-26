package com.crostage.trainchecker.data.model.rout

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RouteResultVol2(
    @SerializedName("GtExpress_Response")
    @Expose
    val response: ResponseV2?,
)

data class ResponseV2(
    @SerializedName("Routes")
    @Expose
    val routes: List<Routes>?,
)
