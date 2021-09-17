package com.crostage.trainchecker.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.crostage.trainchecker.R
import com.crostage.trainchecker.databinding.ItemCarBinding
import com.crostage.trainchecker.domain.model.Car

/**
 * Адаптер для отображения списка вагонов
 *
 */
class CarListAdapter : RecyclerView.Adapter<CarViewHolder>() {

    private var dataList = listOf<Car>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<Car>) {
        dataList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_car, parent, false)
        return CarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}

class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemCarBinding.bind(itemView)

    @SuppressLint("SetTextI18n")
    fun bind(car: Car) {

        binding.carNumber.text = car.carNumber
        binding.carType.text = car.carType
        binding.clsType.text = car.clsType
        binding.tariff.text = car.tariff + " руб"

        val ticketsBuilder = StringBuilder()
        for (seat in car.seats) {
            ticketsBuilder.append("${seat.label} : ${seat.free}\n")
        }
        binding.ticketList.text = ticketsBuilder.toString()

    }

}