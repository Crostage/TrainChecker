package com.crostage.trainchecker.model.data.rout.error

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RouteRequestError(
    @SerializedName("GtExpress_Response")
    @Expose
    val response: ErrorResponse?,
)

data class ErrorResponse(
    @SerializedName("Error")
    @Expose
    val error: Error,
)

data class Error(
    val content: String,
)