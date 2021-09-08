package com.crostage.trainchecker.presentation.adapter

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.crostage.trainchecker.R
import com.crostage.trainchecker.databinding.ItemTrainBinding
import com.crostage.trainchecker.domain.model.Train
import com.crostage.trainchecker.presentation.fragment.FavouriteInsertListener
import com.crostage.trainchecker.presentation.fragment.FavouriteRemoveListener
import com.crostage.trainchecker.presentation.fragment.TrainItemClickListener

class TrainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemTrainBinding.bind(itemView)

    private var favouriteInsertListener: FavouriteInsertListener? = null


    fun setInsertListener(insertListener: FavouriteInsertListener) {
        favouriteInsertListener = insertListener
    }

    fun bind(
        train: Train,
        favouriteRemoveListener: FavouriteRemoveListener,
        trainItemClickListener: TrainItemClickListener,
    ) {


        itemView.setOnClickListener {
            trainItemClickListener.trainClicked(train)
        }

        binding.dayMode.text = train.brand
        binding.trainNumber.text = train.trainNumber
        binding.trainType.text = train.carrier
        binding.startTime.text = train.timeStart
        binding.endTime.text = train.timeEnd
        binding.inWay.text = train.timeInWay
        binding.startDate.text = train.dateStart
        binding.endDate.text = train.dateEnd

        changeFavourite(train.isFavourite)

        binding.favourite.setOnClickListener {
            if (!train.isFavourite) {
                favouriteInsertListener?.addTrainToFavourite(train)
                changeFavourite(true)
                train.isFavourite = true
            } else {
                favouriteRemoveListener.removeTrainToFavourite(train)
                changeFavourite(false)
                train.isFavourite = false
            }
        }

    }

    private fun changeFavourite(favouriteRemoveListener: Boolean) {
        if (favouriteRemoveListener) {
            binding.favourite.setImageDrawable(ContextCompat.getDrawable(
                itemView.context,
                R.drawable.ic_favorite_true
            ))
        } else {
            binding.favourite.setImageDrawable(ContextCompat.getDrawable(
                itemView.context,
                R.drawable.ic_favorite
            ))
        }
    }

}

