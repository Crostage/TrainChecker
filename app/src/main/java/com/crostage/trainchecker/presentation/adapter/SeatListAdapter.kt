package com.crostage.trainchecker.presentation.adapter

import android.annotation.SuppressLint
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.crostage.trainchecker.R
import com.crostage.trainchecker.databinding.ItemSeatBinding
import com.crostage.trainchecker.domain.model.Car

class SeatListAdapter : RecyclerView.Adapter<SeatViewHolder>() {

    private var dataList = listOf<Car>()

    fun setData(list: List<Car>) {
        dataList = list
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

class SeatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemSeatBinding.bind(itemView)

    @SuppressLint("SetTextI18n")
    fun bind(car: Car) {

        binding.carNumber.text = car.carNumber
        binding.carType.text = car.carType
        binding.clsType.text = car.clsType
        binding.tariff.text = car.tariff + " руб"

        binding.ticketList.removeAllViews()
        for (seat in car.tickets) {
            val textView = TextView(itemView.context)
            textView.gravity = Gravity.START
            textView.text = "${seat.label} : ${seat.free}"
            binding.ticketList.addView(textView)
        }


    }

}