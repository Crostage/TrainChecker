package com.crostage.trainchecker.data.model.train

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SearchResult(
    @SerializedName("tp")
    @Expose
    val result: List<TrainResponse>,
)

class TrainResponse(
    val list: List<TrainEntity>,
)

