package com.crostage.trainchecker.presentation.interfaces

import com.crostage.trainchecker.domain.model.Train

/**
 * Вспомогательный интерфейс для добавления поезда в отслеживаемые
 *
 */
interface FavouriteInsertListener {
    fun addTrainToFavourite(train: Train)
}

/**
 * Вспомогательный интерфейс для удаления поезда из отслеживаемых
 *
 */
interface FavouriteRemoveListener {
    fun removeTrainToFavourite(train: Train)
}

/**
 * Вспомогательный интерфейс перехода в детальную информацию по клику
 *
 */
interface TrainItemClickListener {
    fun trainClicked(train: Train)
}