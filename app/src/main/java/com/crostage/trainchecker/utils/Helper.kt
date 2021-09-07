package com.crostage.trainchecker.utils

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
            val sdf = SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH)
            val date = Date()
            return sdf.format(date)
        }

        fun checkFavouritesOnActualDate(list: List<Train>): List<Train> {
            val sdf = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.ENGLISH)
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
//            snackBar.setActionTextColor(Color.BLUE)
            val snackBarView = snackBar.view
//            snackBarView.setBackgroundColor(Color.LTGRAY)
            val textView =
                snackBarView.findViewById<TextView>(R.id.snackbar_text)
            textView.setTextColor(Color.WHITE)
            textView.gravity = View.TEXT_ALIGNMENT_CENTER
//            textView.textSize = 28f
            snackBar.show()
        }

    }
}