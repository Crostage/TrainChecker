package com.crostage.trainchecker.data.model.train


class TrainResponse(
    val list: List<TrainEntity>?,
    val msgList: List<ErrorMessage>,
)

class ErrorMessage(
    val message: String?,
)


