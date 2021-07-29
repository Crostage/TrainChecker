package com.crostage.trainchecker

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.crostage.trainchecker.helper.Constant


class SearchFragment : Fragment(R.layout.fragment_search) {


    private lateinit var cityFrom: EditText
    private lateinit var cityTo: EditText
    private lateinit var date: EditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cityFrom = view.findViewById(R.id.etCityFrom)
        cityTo = view.findViewById(R.id.etCityTo)
        date = view.findViewById(R.id.etDate)

        view.findViewById<Button>(R.id.btnSearch).setOnClickListener {
            btnClickListener()
        }
    }


    private fun btnClickListener() {


        if (cityFrom.text.isEmpty() || cityTo.text.isEmpty() || date.text.isEmpty()) {
            Toast.makeText(activity, "Заполните все поля", Toast.LENGTH_SHORT).show()
            return
        }

        val bundle = Bundle()

        bundle.putString(Constant.SEARCH_CITY_FROM, "${cityFrom.text}")
        bundle.putString(Constant.SEARCH_CITY_TO, "${cityTo.text}")
        bundle.putString(Constant.SEARCH_DATE, "${date.text}")

        findNavController().navigate(R.id.action_searchFragment_to_searchResultFragment, bundle)
    }

}