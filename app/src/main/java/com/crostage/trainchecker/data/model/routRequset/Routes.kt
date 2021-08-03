package com.crostage.trainchecker.data.model.routRequset

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Routes(
    @SerializedName("Stop")
    @Expose
    val routList: List<TrainStop>,
    @SerializedName("Title")
    @Expose
    val title: String
)