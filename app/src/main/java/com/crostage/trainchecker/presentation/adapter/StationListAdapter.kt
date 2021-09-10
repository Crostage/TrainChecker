package com.crostage.trainchecker.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.crostage.trainchecker.R
import com.crostage.trainchecker.domain.model.Station
import com.crostage.trainchecker.presentation.fragment.OnStationClick

class StationListAdapter(private val onStationClick: OnStationClick) :
    RecyclerView.Adapter<StationViewHolder>() {


    private var dataList = listOf<Station>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<Station>) {
        dataList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_station, parent, false)
        val viewHolder = StationViewHolder(view)

        viewHolder.itemView.setOnClickListener {
            onStationClick.onStationClick(dataList[viewHolder.adapterPosition])
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: StationViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}

class StationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(station: Station) {
        val stationName = itemView.findViewById<TextView>(R.id.stationName)
        stationName.text = station.stationName
    }
}