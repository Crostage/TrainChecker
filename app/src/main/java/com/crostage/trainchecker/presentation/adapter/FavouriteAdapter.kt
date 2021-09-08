package com.crostage.trainchecker.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.crostage.trainchecker.R
import com.crostage.trainchecker.domain.model.Train
import com.crostage.trainchecker.presentation.adapter.diffutil.TrainDiffUtil
import com.crostage.trainchecker.presentation.fragment.FavouriteRemoveListener
import com.crostage.trainchecker.presentation.fragment.TrainItemClickListener

open class FavouriteAdapter(
    private val favouriteRemoveListener: FavouriteRemoveListener,
    private val trainItemClickListener: TrainItemClickListener,
) :
    RecyclerView.Adapter<TrainViewHolder>() {

    private var dataList = listOf<Train>()

    fun setData(list: List<Train>) {
        val diffCallback = TrainDiffUtil(dataList, list)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        dataList = list
        diffResult.dispatchUpdatesTo(this)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_train, parent, false)

        return TrainViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrainViewHolder, position: Int) {
        holder.bind(dataList[position], favouriteRemoveListener, trainItemClickListener)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}