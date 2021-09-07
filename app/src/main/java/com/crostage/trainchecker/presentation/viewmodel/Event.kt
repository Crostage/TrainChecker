package com.crostage.trainchecker.presentation.viewmodel

/**
 * Класс событие - обертка над объектом, без повторного использования
 *
 * @param T
 * @property content тип рассматриваемого объекта
 */
open class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set //только чтение

    /**
     * Возвращает содержимое и предотвращает его повторное использование.
     *
     * @return входной объект
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

}