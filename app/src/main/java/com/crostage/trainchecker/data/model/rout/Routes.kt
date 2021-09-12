package com.crostage.trainchecker.data.model.rout

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Routes(
    @SerializedName("Stop")
    @Expose
    val routList: List<TrainStopDto>,
)

data class Response(
    val routes: List<TrainStopDto>,
    @SerializedName("Error")
    val error: RoutesError?,
)

data class RoutesError(
    val content: String,
)

