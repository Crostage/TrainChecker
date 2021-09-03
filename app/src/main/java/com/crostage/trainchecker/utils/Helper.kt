package com.crostage.trainchecker.utils

import android.annotation.SuppressLint
import com.crostage.trainchecker.domain.model.Train
import java.text.SimpleDateFormat
import java.util.*

class Helper {
    companion object {
        fun getActualDate(): String {
            val c = Calendar.getInstance()
            val mYear = c.get(Calendar.YEAR)
            val mMonth = c.get(Calendar.MONTH)
            val mDay = c.get(Calendar.DAY_OF_MONTH)

            val month = if (mMonth.toString().length == 1) {
                "0${mMonth + 1}"
            } else "${mMonth + 1}"

            return "$mDay.$month.$mYear"
        }


        @SuppressLint("SimpleDateFormat")
        fun checkFavouriteOnActualDate(list: List<Train>): List<Train> {

            val sdf = SimpleDateFormat("dd.MM.yyyy hh:mm")
            val currentDate = Date().time

            return list.filter {
                val stringTrainTime = it.dateStart + " " + it.timeStart
                val longTrainTime = sdf.parse(stringTrainTime)?.time
                currentDate < longTrainTime!!
            }
        }

    }
}