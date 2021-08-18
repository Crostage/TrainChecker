package com.crostage.trainchecker.presentation.adapter

import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.crostage.trainchecker.databinding.ItemSeatBinding
import com.crostage.trainchecker.model.data.seat.Car

class SeatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemSeatBinding.bind(itemView)

    fun bind(car: Car) {

        binding.carNumber.text = car.carNumber
        binding.carType.text = car.carType
        binding.clsType.text = car.clsType
        binding.tariff.text = car.tariff

        for (seat in car.tickets) {
            val textView = TextView(itemView.context)
            textView.gravity = Gravity.CENTER
            textView.text = "${seat.label} : ${seat.free} : ${seat.tariff}руб"
            binding.ticketList.addView(textView)
        }


    }

}