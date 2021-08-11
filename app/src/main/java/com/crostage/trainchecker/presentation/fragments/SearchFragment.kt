package com.crostage.trainchecker.presentation.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.crostage.trainchecker.R
import com.crostage.trainchecker.presentation.StationChoiseActivity
import com.crostage.trainchecker.utils.Constant
import com.crostage.trainchecker.utils.Helper
import java.util.*


class SearchFragment : Fragment(R.layout.fragment_search) {

    private lateinit var cityFrom: TextView
    private lateinit var cityTo: TextView
    private lateinit var date: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cityFrom = view.findViewById(R.id.etCityFrom)
        cityFrom.text = "Москва"
        cityTo = view.findViewById(R.id.etCityTo)
        cityTo.text = "Сочи"
        date = view.findViewById(R.id.tvDate)

        date.apply {
            setOnClickListener {
                dataPick(date)
            }
            date.setText(Helper.getActualDate())
        }

        view.findViewById<Button>(R.id.btnSearch).setOnClickListener {
            btnClickListener()
        }

        cityFrom.setOnClickListener { pickStation() }
        cityTo.setOnClickListener { pickStation() }


    }


    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                val data: Intent? = result.data

            }
        }

    private fun pickStation() {
        val intent = Intent(activity, StationChoiseActivity::class.java)
        resultLauncher.launch(intent)
    }


    @SuppressLint("SetTextI18n")
    private fun dataPick(textView: TextView) {
        val c = Calendar.getInstance()
        val mYear = c.get(Calendar.YEAR)
        val mMonth = c.get(Calendar.MONTH)
        val mDay = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(requireActivity(), { _, year, monthOfYear, dayOfMonth ->

            val month = if (monthOfYear.toString().length == 1) {
                "0${monthOfYear + 1}"
            } else "${monthOfYear + 1}"

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