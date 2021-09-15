package com.crostage.trainchecker.presentation.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.crostage.trainchecker.domain.model.Train

class TrainDiffUtil(
    private val oldList: List<Train>,
    private val newList: List<Train>,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem == newItem
    }

    //в моем случае необходимо только проверять поле isFavourite, тк в остальные моменты списки
    //поездов меняются целиком
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.isFavourite == newItem.isFavourite
    }
}