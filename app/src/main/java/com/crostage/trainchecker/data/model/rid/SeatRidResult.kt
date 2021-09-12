package com.crostage.trainchecker.data.model.rid

import com.crostage.trainchecker.data.model.train.TrainResponse
import com.google.gson.annotations.SerializedName

data class SeatRidResult(
    @SerializedName("RID")
    val requestId: Long,
)