package com.crostage.trainchecker.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.crostage.trainchecker.R
import com.crostage.trainchecker.databinding.ItemRouteBinding
import com.crostage.trainchecker.domain.model.TrainStop

class RouteListAdapter: RecyclerView.Adapter<RouteViewHolder>() {

    private var dataList = listOf<TrainStop>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<TrainStop>) {
        dataList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RouteViewHolder {
        val view  =  LayoutInflater.from(parent.context)
            .inflate(R.layout.item_route, parent, false)
        return RouteViewHolder(view)
    }

    override fun onBindViewHolder(holder: RouteViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}


class RouteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    private val binding = ItemRouteBinding.bind(itemView)


    fun bind(route: TrainStop) {

        binding.stationName.text = route.stationName
        binding.timeOnStation.text = route.arriveTime
        binding.distance.text = route.distance.toString()
    }
}