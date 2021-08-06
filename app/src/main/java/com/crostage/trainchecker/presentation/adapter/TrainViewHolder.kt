package com.crostage.trainchecker.presentation.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.crostage.trainchecker.R
import com.crostage.trainchecker.data.model.trainRequest.Train

class TrainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    fun bind(train: Train) {
        val trainNumber = itemView.findViewById<TextView>(R.id.trainNumber)
        val trainType = itemView.findViewById<TextView>(R.id.trainType)
        val dayMode = itemView.findViewById<TextView>(R.id.dayMode)
        val startTime = itemView.findViewById<TextView>(R.id.startTime)
        val endTime = itemView.findViewById<TextView>(R.id.endTime)
        val inWay = itemView.findViewById<TextView>(R.id.inWay)


        trainNumber.text = train.trainNumber
        trainType.text = train.carrier
        dayMode.text = train.subtrainCatName
        startTime.text = train.timeStart
        endTime.text = train.timeEnd
        inWay.text = train.timeInWay

    }

    }