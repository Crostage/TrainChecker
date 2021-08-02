package com.crostage.trainchecker.data.model.trainRequest

 class SearchResult(
    val tp: List<Tp>,
    val message:String
)

 class Tp(
    val list: List<Train>
)