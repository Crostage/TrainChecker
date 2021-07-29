package com.crostage.trainchecker.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.crostage.trainchecker.R
import com.crostage.trainchecker.model.trainRequest.Train

class TrainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    fun bind(train: Train) {
        val trainNumber = itemView.findViewById<TextView>(R.id.trainNumber)
        val trainType = itemView.findViewById<TextView>(R.id.trainType)
        val dayMode = itemView.findViewById<TextView>(R.id.dayMode)
        val tvFrom = itemView.findViewById<TextView>(R.id.tvFrom)
        val tvTo = itemView.findViewById<TextView>(R.id.tvTo)
        val startTime = itemView.findViewById<TextView>(R.id.startTime)
        val endTime = itemView.findViewById<TextView>(R.id.endTime)
        val inWay = itemView.findViewById<TextView>(R.id.inWay)


        trainNumber.text = train.number
        trainType.text = train.carrier
        dayMode.text = train.subtrainCatName
        tvFrom.text = train.route0
        tvTo.text = train.route1
        startTime.text = train.time0
        endTime.text = train.time1
        inWay.text = train.timeInWay

    }

}