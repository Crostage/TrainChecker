package com.crostage.trainchecker.presentation.interfaces

import com.crostage.trainchecker.domain.model.Train

interface FavouriteInsertListener {
    fun addTrainToFavourite(train: Train)
}

interface FavouriteRemoveListener {
    fun removeTrainToFavourite(train: Train)
}

interface TrainItemClickListener {
    fun trainClicked(train: Train)
}