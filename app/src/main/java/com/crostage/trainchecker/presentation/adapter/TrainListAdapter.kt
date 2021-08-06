package com.crostage.trainchecker.presentation.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.crostage.trainchecker.R
import com.crostage.trainchecker.data.model.trainRequest.Train
import com.crostage.trainchecker.utils.Constant

class TrainListAdapter : RecyclerView.Adapter<TrainViewHolder>() {

    private var dataList = mutableListOf<Train>()

    fun setData(list: List<Train>) {
        dataList = list as MutableList<Train>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_train, parent, false)
        val viewHolder = TrainViewHolder(view)
        viewHolder.itemView.setOnClickListener {

            val bundle = Bundle()

            bundle.putSerializable(Constant.TRAIN_ROUTS,dataList[viewHolder.adapterPosition])

            view.findNavController().navigate(R.id.action_searchResultFragment_to_routesFragment,bundle)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: TrainViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}

