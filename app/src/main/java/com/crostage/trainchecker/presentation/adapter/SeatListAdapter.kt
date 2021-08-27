package com.crostage.trainchecker.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.crostage.trainchecker.R
import com.crostage.trainchecker.model.data.seat.Car

class SeatListAdapter : RecyclerView.Adapter<SeatViewHolder>() {

    private var dataList = mutableListOf<Car>()

    fun setData(list: List<Car>) {
        dataList = list as MutableList<Car>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_seat, parent, false)
        return SeatViewHolder(view)
    }

    override fun onBindViewHolder(holder: SeatViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}