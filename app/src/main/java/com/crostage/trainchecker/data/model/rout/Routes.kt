package com.crostage.trainchecker.data.model.rout

import com.google.gson.annotations.SerializedName

data class Routes(
    @SerializedName("Stop")
    val routList: List<TrainStopDto>,
)

data class Response(
    @SerializedName("Routes")
    val routes: List<TrainStopDto>,
    @SerializedName("Error")
    val error: RoutesError?,
)

data class RoutesError(
    val content: String,
)

