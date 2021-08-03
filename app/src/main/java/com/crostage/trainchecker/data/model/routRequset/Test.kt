package com.crostage.trainchecker.data.model.routRequset

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Test(
    @SerializedName("Routes")
    @Expose
    val routes: Routes
) {
}