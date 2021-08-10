package com.crostage.trainchecker.data.model.routRequset

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RoutesResult(
    @SerializedName("GtExpress_Response")
    @Expose
    val response: Response
)

data class Routes(
    @SerializedName("Stop")
    @Expose
    val routList: List<TrainStop>,
    @SerializedName("Title")
    @Expose
    val title: String
)

data class Response(
    @SerializedName("Routes")
    @Expose
    val routes: Routes
)

data class TrainStop(
    val ArvTime: String,
    val Code: Int,
    val Days: String,
    val DepTime: String,
    val Distance: Int,
    val Station: String,
    val TailForward: String,
    val WaitingTime: String
)