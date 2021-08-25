package com.crostage.trainchecker.data.model.rout.error

data class RouteRequestError(
    val GtExpress_Response: GtExpressResponse,
)

data class GtExpressResponse(
    val Error: Error,
)

data class Error(
    val Code: Int,
    val content: String,
)