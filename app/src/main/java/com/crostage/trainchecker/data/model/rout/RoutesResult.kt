package com.crostage.trainchecker.data.model.rout

import androidx.annotation.Nullable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RoutesResult(
    @SerializedName("GtExpress_Response")
    @Expose
    val response: Response,
)

data class Routes(
    @SerializedName("Stop")
    @Expose
    val routList: List<TrainStop>,
    @SerializedName("Title")
    @Expose
    val title: String,
)

data class Response(
    @SerializedName("Routes")
    @Expose
    val routes: Routes
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
    @SerializedName("WaitingTime")
    @Expose
    val waitingTime: String,
)