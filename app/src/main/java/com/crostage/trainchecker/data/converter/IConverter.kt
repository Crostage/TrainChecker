package com.crostage.trainchecker.data.converter

interface IConverter<T, R> {

    fun convert(t: T): R

    fun revers(r: R): T

}