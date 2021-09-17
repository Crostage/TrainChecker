package com.crostage.trainchecker.presentation.adapter

import com.crostage.trainchecker.presentation.interfaces.FavouriteInsertListener
import com.crostage.trainchecker.presentation.interfaces.FavouriteRemoveListener
import com.crostage.trainchecker.presentation.interfaces.TrainItemClickListener

/**
 * Адаптер списка найденых поездов
 *
 * @property favouriteInsertListener слушатель для вставки в избранные
 * @constructor
 *
 * @param favouriteRemoveListener слушатель удаления из избранных
 * @param trainItemClickListener слушатель кликов по элементу списка
 */
class TrainListAdapter(
    private val favouriteInsertListener: FavouriteInsertListener,
    favouriteRemoveListener: FavouriteRemoveListener,
    trainItemClickListener: TrainItemClickListener,
) : FavouriteAdapter(favouriteRemoveListener, trainItemClickListener) {

    override fun onBindViewHolder(holder: TrainViewHolder, position: Int) {
        holder.setInsertListener(favouriteInsertListener)
        super.onBindViewHolder(holder, position)

    }


}
