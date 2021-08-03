package com.crostage.trainchecker.data.model

data class BaseRoutesRequest(
    val type: String,
    val rid: Long,
    val fail_msg: String
)