package com.crostage.trainchecker.data.model.train

import com.google.gson.annotations.SerializedName

class SearchResult(
    @SerializedName("tp")
    val listResponse: List<TrainResponse>?,
    val result: String,
    @SerializedName("RID")
    val requestId: Long?,
)

class TrainResponse(
    val list: List<TrainEntity>,
    val msgList: List<ErrorMessage>,
)

class ErrorMessage(
    val message: String,
)


