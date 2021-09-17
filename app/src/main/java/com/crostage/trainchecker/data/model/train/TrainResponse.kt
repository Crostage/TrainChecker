package com.crostage.trainchecker.data.model.train


/**
 * Модель ответа от сервера для списка поездов
 *
 * @property list список поездов [TrainEntity]
 * @property msgList список ошибок [ErrorMessage]
 */
class TrainResponse(
    val list: List<TrainEntity>?,
    val msgList: List<ErrorMessage>,
)

/**
 * Модель ошибок для запроса поездов
 *
 * @property message текст ошибки
 */
class ErrorMessage(
    val message: String?,
)


