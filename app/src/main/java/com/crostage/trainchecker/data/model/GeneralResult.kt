package com.crostage.trainchecker.data.model

import com.crostage.trainchecker.data.model.rout.Response
import com.crostage.trainchecker.data.model.seat.CarResponse
import com.crostage.trainchecker.data.model.train.TrainResponse
import com.google.gson.annotations.SerializedName

class GeneralResult(
    @SerializedName("lst")
    val listCarResponse: List<CarResponse>,
    @SerializedName("GtExpress_Response")
    val response: Response?,
    @SerializedName("tp")
    val listResponse: List<TrainResponse>?,
    @SerializedName("RID")
    val requestId: Long?,
)