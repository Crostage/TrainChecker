package com.crostage.trainchecker.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.crostage.trainchecker.R
import com.crostage.trainchecker.domain.model.Station
import com.crostage.trainchecker.presentation.activity.OnStationClick

class StationListAdapter(private val onStationClick: OnStationClick) :
    RecyclerView.Adapter<StationViewHolder>() {


    private var dataList = listOf<Station>()

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
