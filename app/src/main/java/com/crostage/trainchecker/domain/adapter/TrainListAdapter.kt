package com.crostage.trainchecker.domain.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.crostage.trainchecker.R
import com.crostage.trainchecker.data.model.trainRequest.Train

class TrainListAdapter : RecyclerView.Adapter<TrainViewHolder>() {

    var dataList = mutableListOf<Train>()

    fun setData(list: List<Train>) {
        dataList = list as MutableList<Train>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_train, parent, false)
        return TrainViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrainViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}

