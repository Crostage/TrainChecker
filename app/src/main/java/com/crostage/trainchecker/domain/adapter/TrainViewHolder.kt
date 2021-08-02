package com.crostage.trainchecker.domain.adapter

import android.view.View
import android.widget.TextView
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.crostage.trainchecker.R
import com.crostage.trainchecker.data.model.trainRequest.Train
import com.google.android.material.bottomnavigation.BottomNavigationView

class TrainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    fun bind(train: Train) {
        val trainNumber = itemView.findViewById<TextView>(R.id.trainNumber)
        val trainType = itemView.findViewById<TextView>(R.id.trainType)
        val dayMode = itemView.findViewById<TextView>(R.id.dayMode)
        val startTime = itemView.findViewById<TextView>(R.id.startTime)
        val endTime = itemView.findViewById<TextView>(R.id.endTime)
        val inWay = itemView.findViewById<TextView>(R.id.inWay)


        trainNumber.text = train.number
        trainType.text = train.carrier
        dayMode.text = train.subtrainCatName
        startTime.text = train.time0
        endTime.text = train.time1
        inWay.text = train.timeInWay

    }

    }