package com.crostage.trainchecker.utils

import retrofit2.Call
import retrofit2.Response
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


        fun <T> Call<T>.executeAndExceptionChek(): Response<T>? {
            try {
                val response = execute()
                when (response.code()) {
                    200 -> return response
                    401 -> throw Error401()
                    404 -> throw Error404()

                }
            } catch (e: Exception) {
                throw ErrorConnections()
            }
            return null
        }


    }
}