package com.crostage.trainchecker.data.converter


/**
 * Конверер для преобразования data -> domain
 *
 * @param T входное значение для преобразования
 * @param R выходное преобразованное значение
 */

interface IConverter<T, R> {

    /**
     * Прямое преобразование
     *
     * @param input значение для преобразования
     * @return преобразованное значение
     */
    fun convert(input: T): R

    /**
     * Обратное преобразование
     *
     * @param input значение для преобразования
     * @return преобразованное значение
     */
    fun revers(input: R): T

}