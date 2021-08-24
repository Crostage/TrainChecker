package com.crostage.trainchecker.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.crostage.trainchecker.databinding.ItemRouteBinding
import com.crostage.trainchecker.data.model.rout.TrainStop

class RouteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    private val binding = ItemRouteBinding.bind(itemView)


    fun bind(route: TrainStop) {

        binding.stationName.text = route.stationName
        binding.timeOnStation.text = route.arriveTime
        binding.distance.text = route.distance.toString()
    }
}