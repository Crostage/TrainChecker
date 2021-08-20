package com.crostage.trainchecker.presentation.adapter

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.crostage.trainchecker.R
import com.crostage.trainchecker.databinding.ItemTrainBinding
import com.crostage.trainchecker.model.data.train.Train

class TrainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var isFavourite = false

    private val binding = ItemTrainBinding.bind(itemView)
    val favourite = binding.favourite

    fun bind(train: Train, list: List<Train>) {

        binding.dayMode.text = train.brand
        binding.trainNumber.text = train.trainNumber
        binding.trainType.text = train.carrier
        binding.startTime.text = train.timeStart
        binding.endTime.text = train.timeEnd
        binding.inWay.text = train.timeInWay
        binding.startDate.text = train.dateStart
        binding.endDate.text = train.dateEnd


        isFavourite = if (list.contains(train)) {
            binding.favourite.setImageDrawable(ContextCompat.getDrawable(
                itemView.context,
                R.drawable.ic_favorite_true
            ))
            true
        } else {
            binding.favourite.setImageDrawable(ContextCompat.getDrawable(
                itemView.context,
                R.drawable.ic_favorite
            ))
            false
        }
//
    }


}
