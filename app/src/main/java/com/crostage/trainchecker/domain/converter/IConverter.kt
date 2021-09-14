package com.crostage.trainchecker.domain.converter

interface IConverter<T, R> {

    fun convert(input: T): R

    fun revers(input: R): T

}