package com.crostage.trainchecker.presentation.adapter

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.crostage.trainchecker.R
import com.crostage.trainchecker.data.model.stationRequest.Station
import com.crostage.trainchecker.utils.Constant

class StationListAdapter : RecyclerView.Adapter<StationViewHolder>() {


    private var dataList = mutableListOf<Station>()

    fun setData(list: List<Station>) {
        dataList = list as MutableList<Station>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_station, parent, false)
        val viewHolder = StationViewHolder(view)
        viewHolder.itemView.setOnClickListener {
            val intent = Intent()
            intent.putExtra(Constant.STATION, dataList[viewHolder.adapterPosition])
            (parent.context as Activity).setResult(Activity.RESULT_OK,intent)
            (parent.context as Activity).finish()
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
