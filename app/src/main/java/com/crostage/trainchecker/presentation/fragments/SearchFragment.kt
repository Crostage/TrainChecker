package com.crostage.trainchecker.presentation.fragments

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.crostage.trainchecker.R
import com.crostage.trainchecker.utils.Constant
import java.util.*


class SearchFragment : Fragment(R.layout.fragment_search) {

    private lateinit var cityFrom: EditText
    private lateinit var cityTo: EditText
    private lateinit var date: EditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cityFrom = view.findViewById(R.id.etCityFrom)
        cityFrom.setText("Москва")
        cityTo = view.findViewById(R.id.etCityTo)
        cityTo.setText("Сочи")
        date = view.findViewById(R.id.tvDate)

        date.apply {
            setOnClickListener {
                dataPick(date)
            }
            date.setText(getActualDate())
        }

        view.findViewById<Button>(R.id.btnSearch).setOnClickListener {
            btnClickListener()
        }

    }

    private fun getActualDate(): String {
        val c = Calendar.getInstance()
        val mYear = c.get(Calendar.YEAR)
        val mMonth = c.get(Calendar.MONTH)
        val mDay = c.get(Calendar.DAY_OF_MONTH)

        val month = if (mMonth.toString().length == 1) {
            "0${mMonth+1}"
        } else "${mMonth+1}"

        return "$mDay.$month.$mYear"
    }

    @SuppressLint("SetTextI18n")
    private fun dataPick(textView: TextView) {
        val c = Calendar.getInstance()
        val mYear = c.get(Calendar.YEAR)
        val mMonth = c.get(Calendar.MONTH)
        val mDay = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(requireActivity(), { _, year, monthOfYear, dayOfMonth ->

            val month = if (monthOfYear.toString().length == 1) {
                "0${monthOfYear+1}"
            } else "${monthOfYear+1}"

            textView.text = "$dayOfMonth.$month.$year"

        }, mYear, mMonth, mDay)

        dpd.show()
    }


    private fun btnClickListener() {

        if (cityFrom.text.isEmpty() || cityTo.text.isEmpty() || date.text.isEmpty()) {
            Toast.makeText(activity, getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show()
            return
        }

        val bundle = Bundle()

        bundle.putString(Constant.SEARCH_CITY_FROM, "${cityFrom.text}")
        bundle.putString(Constant.SEARCH_CITY_TO, "${cityTo.text}")
        bundle.putString(Constant.SEARCH_DATE, "${date.text}")

        findNavController().navigate(R.id.action_searchFragment_to_searchResultFragment, bundle)
    }

}