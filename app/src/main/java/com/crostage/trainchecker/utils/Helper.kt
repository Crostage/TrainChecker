package com.crostage.trainchecker.utils

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.View
import android.widget.TextView
import com.crostage.trainchecker.R
import com.crostage.trainchecker.domain.model.Train
import com.google.android.material.snackbar.Snackbar
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

        fun showNewSnack(view: View, text: String) {

            //todo сделать покрасивее
            val snackBar = Snackbar.make(view, text,
                Snackbar.LENGTH_SHORT).setAction("Action", null)
            snackBar.setActionTextColor(Color.BLUE)
            val snackBarView = snackBar.view
//            snackBarView.setBackgroundColor(Color.LTGRAY)
            val textView =
                snackBarView.findViewById<TextView>(R.id.snackbar_text)
            textView.setTextColor(Color.BLUE)
            textView.gravity = View.TEXT_ALIGNMENT_CENTER
//            textView.textSize = 28f
            snackBar.show()
        }

    }
}