package com.crostage.trainchecker.utils

import java.lang.Exception

sealed class TrainExceptions: Exception()
 class Error404(override val message: String?="Сервер не отвечает"):TrainExceptions()
 class Error401(override val message: String? = "Проблемы при авторизации") : TrainExceptions()
class ErrorConnections(override val message: String? = "Не удалось получить данные") :
    TrainExceptions()

