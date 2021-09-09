package com.crostage.trainchecker.presentation.adapter

import com.crostage.trainchecker.presentation.interfaces.FavouriteInsertListener
import com.crostage.trainchecker.presentation.interfaces.FavouriteRemoveListener
import com.crostage.trainchecker.presentation.interfaces.TrainItemClickListener


class TrainListAdapter(
    private val favouriteInsertListener: FavouriteInsertListener,
    private val favouriteRemoveListener: FavouriteRemoveListener,
    private val trainItemClickListener: TrainItemClickListener,
) : FavouriteAdapter(favouriteRemoveListener, trainItemClickListener) {

    override fun onBindViewHolder(holder: TrainViewHolder, position: Int) {
        holder.setInsertListener(favouriteInsertListener)
        super.onBindViewHolder(holder, position)

    }


}
