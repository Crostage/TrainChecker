package com.crostage.trainchecker.presentation.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.crostage.trainchecker.R
import com.crostage.trainchecker.domain.model.Train
import com.crostage.trainchecker.presentation.fragment.FavouriteClickListener
import com.crostage.trainchecker.utils.Constant

class TrainListAdapter(private val callback: FavouriteClickListener) :
    RecyclerView.Adapter<TrainViewHolder>() {

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

            //todo убрать фрагмент из адаптера
            val bundle = Bundle()
            bundle.putSerializable(Constant.TRAIN_ARG, dataList[viewHolder.adapterPosition])
            view.findNavController()
                .navigate(R.id.detailFragment, bundle)
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: TrainViewHolder, position: Int) {
        holder.bind(dataList[position], callback)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}

