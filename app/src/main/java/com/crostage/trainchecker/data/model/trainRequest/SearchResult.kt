package com.crostage.trainchecker.data.model.trainRequest

 class SearchResult(
    val tp: List<Tp>,
    val result:String
)

 class Tp(
    val list: List<Train>
)