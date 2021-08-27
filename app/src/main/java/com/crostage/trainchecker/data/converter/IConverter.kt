package com.crostage.trainchecker.data.converter

interface IConverter<T, R> {

    fun convert(input: T): R

    fun revers(input: R): T

}