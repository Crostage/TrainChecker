package com.crostage.trainchecker.presentation.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.crostage.trainchecker.R
import com.crostage.trainchecker.model.station.Station


class StationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(station: Station) {
        val stationName = itemView.findViewById<TextView>(R.id.stationName)
        stationName.text = station.stationName
    }
}