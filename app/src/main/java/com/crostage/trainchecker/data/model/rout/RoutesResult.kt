package com.crostage.trainchecker.data.model.rout

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RoutesResult(
    @SerializedName("GtExpress_Response")
    @Expose
    val response: Response?,
)

data class Routes(
    @SerializedName("Stop")
    @Expose
    val routList: List<TrainStopDto>,
)

data class Response(
    val routes: List<TrainStopDto>,
)

data class RoutesError(
    val content: String,
)

