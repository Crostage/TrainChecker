package com.crostage.trainchecker.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.crostage.trainchecker.model.train.Train
import com.crostage.trainchecker.databinding.ItemTrainBinding

class TrainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemTrainBinding.bind(itemView)

    fun bind(train: Train) {

        binding.trainNumber.text = train.trainNumber
        binding.trainType.text = train.carrier
        binding.dayMode.text = train.subtrainCatName
        binding.startTime.text = train.timeStart
        binding.endTime.text = train.timeEnd
        binding.inWay.text = train.timeInWay
        binding.startDate.text = train.dateStart
        binding.endDate.text = train.dateEnd

    }

}