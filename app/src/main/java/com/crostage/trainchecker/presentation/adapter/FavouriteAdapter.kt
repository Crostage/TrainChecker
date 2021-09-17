package com.crostage.trainchecker.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.crostage.trainchecker.R
import com.crostage.trainchecker.domain.model.Train
import com.crostage.trainchecker.presentation.adapter.diffutil.TrainDiffUtil
import com.crostage.trainchecker.presentation.interfaces.FavouriteRemoveListener
import com.crostage.trainchecker.presentation.interfaces.TrainItemClickListener

/**
 * Адаптер для отображения списка отслеживаемых
 *
 * @property favouriteRemoveListener слушатель удаления из избранных
 * @property trainItemClickListener слушатель кликов по элементу списка
 */
open class FavouriteAdapter(
    private val favouriteRemoveListener: FavouriteRemoveListener,
    private val trainItemClickListener: TrainItemClickListener,
) :
    RecyclerView.Adapter<TrainViewHolder>() {

    private var dataList = listOf<Train>()

    fun setData(list: List<Train>) {
        val diffUtil = TrainDiffUtil(dataList, list)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        dataList = list
        diffResult.dispatchUpdatesTo(this)
        println()
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