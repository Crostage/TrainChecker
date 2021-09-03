package com.crostage.trainchecker.presentation.adapter

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.crostage.trainchecker.R
import com.crostage.trainchecker.databinding.ItemTrainBinding
import com.crostage.trainchecker.domain.model.Train
import com.crostage.trainchecker.presentation.fragment.FavouriteClickListener

class TrainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemTrainBinding.bind(itemView)

    fun bind(train: Train, callback: FavouriteClickListener) {

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
                callback.addTrainToFavourite(train)
                changeFavourite(true)
                train.isFavourite = true
            } else {
                callback.removeTrainToFavourite(train)
                changeFavourite(false)
                train.isFavourite = false
            }
        }

    }

    //todo пропадает при вращении

    private fun changeFavourite(isFavourite: Boolean) {
        if (isFavourite) {
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

