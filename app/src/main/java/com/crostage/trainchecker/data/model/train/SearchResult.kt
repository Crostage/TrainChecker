package com.crostage.trainchecker.data.model.train

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SearchResult(
    @SerializedName("tp")
    @Expose
    val listResponse: List<TrainResponse>,
    val result: String,
    @SerializedName("RID")
    val requestId: Long,
)

class TrainResponse(
    val list: List<TrainEntity>,
)


