package com.crostage.trainchecker.presentation.model

/**
 * Класс событие - обертка над объектом, служит для однократного использования оъекта в LiveData
 *
 * @param T
 * @property content тип рассматриваемого объекта
 */
class Event<out T>(private val content: T) {

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

    /**
     * Возвращает содержимое без условий
     *
     */
    fun getContent() = content

}