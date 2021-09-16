package com.crostage.trainchecker.presentation.util

import android.view.View
import com.crostage.trainchecker.domain.model.Train
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

/**
 * Вспомогательный класс для презентационного слоя
 *
 */

class Helper {
    companion object {

        /**
         * Получение актуальной даты в нужном формате
         *
         * @return актуальная дата [String]
         */
        fun getActualDate(): String {
            val sdf = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
            val date = Calendar.getInstance(TimeZone.getTimeZone("GMT+3")).timeInMillis
            return sdf.format(date)
        }

        /**
         * Проверка списка избранных поездов на актуальное время отправления
         *
         * @param list список избранных поездов
         * @return актуальный список поездов [Train]
         */

        fun checkFavouritesOnActualDate(list: List<Train>): List<Train> {
            val sdf = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
            val currentDate = Calendar.getInstance(TimeZone.getTimeZone("GMT+3")).timeInMillis

            return list.filter {
                val stringTrainTime = it.dateStart + " " + it.timeStart
                val longTrainTime = sdf.parse(stringTrainTime)?.time
                currentDate < longTrainTime!!
            }
        }


        /**
         * Демонстрация snack bar
         *
         * @param text техт сообщения
         */

        fun View.showSnackBar(text: String) {
            Snackbar.make(this, text,
                Snackbar.LENGTH_SHORT)
                .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE)
                .setAction("Action", null)
                .show()
        }

    }
}