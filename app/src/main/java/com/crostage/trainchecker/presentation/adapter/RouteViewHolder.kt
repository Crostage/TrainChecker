package com.crostage.trainchecker.presentation.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.crostage.trainchecker.R
import com.crostage.trainchecker.data.model.routRequset.TrainStop

class RouteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(route: TrainStop) {

        val stationName = itemView.findViewById<TextView>(R.id.stationName)
        val time = itemView.findViewById<TextView>(R.id.timeOnStation)
        val distance = itemView.findViewById<TextView>(R.id.distance)

        stationName.text = route.Station
        time.text = route.DepTime
        distance.text = route.Distance.toString()
    }
}