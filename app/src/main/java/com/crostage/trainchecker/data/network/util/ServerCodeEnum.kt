package com.crostage.trainchecker.data.network.util


/**
 * Класс с кодами-ответами от сервера
 *
 * @property code код ответа
 */
enum class ServerCodeEnum(val code: Int) {
    OK(200),
    NOT_FOUND(401),
    NO_AUTH(404)

}