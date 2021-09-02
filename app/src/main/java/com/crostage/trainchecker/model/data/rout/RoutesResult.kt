package com.crostage.trainchecker.model.data.rout

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RoutesResult(
    @SerializedName("GtExpress_Response")
    @Expose
    val response: Response?,
)

data class Routes(
    @SerializedName("Stop")
    @Expose
    val routList: List<TrainStop>?,
) : Serializable

data class Routes2(
    @SerializedName("Stop")
    @Expose
    val routList: List<TrainStop>?,
)

data class Response(
//    @SerializedName("Routes")
    val routes: List<TrainStop>,
//    @SerializedName("Error")
//    val error: Error,
)

data class RoutesError(
    val content: String,
)

data class TrainStop(
    @SerializedName("Code")
    @Expose
    val code: Int,
    @SerializedName("DepTime")
    @Expose
    val arriveTime: String,
    @SerializedName("Distance")
    @Expose
    val distance: Int,
    @SerializedName("Station")
    @Expose
    val stationName: String,
)