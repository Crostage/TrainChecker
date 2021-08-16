package com.crostage.trainchecker.presentation.fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.crostage.trainchecker.R
import com.crostage.trainchecker.databinding.FragmentSearchBinding
import com.crostage.trainchecker.model.data.station.Station
import com.crostage.trainchecker.presentation.activity.StationChoiseActivity
import com.crostage.trainchecker.presentation.viewmodel.factory.SearchViewModel
import com.crostage.trainchecker.utils.Constant
import java.util.*


class SearchFragment : Fragment(R.layout.fragment_search) {

    private var codeFrom = 0
    private var codeTo = 0
    private var isFrom = false
    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

        viewModel.getStationFrom()?.let {
            codeFrom = it.stationCode
            binding.cityFrom.text = it.stationName
        }

        viewModel.getStationTo()?.let {
            codeTo = it.stationCode
            binding.cityTo.text = it.stationName
        }


        binding.tvDate.apply {
            setOnClickListener {
                dataPick(this)
            }
            this.text = viewModel.getDate()
        }


        binding.btnSearch.setOnClickListener {
            btnClickListener()
        }

        binding.cityFrom.setOnClickListener {
            isFrom = true
            pickStation()
        }
        binding.cityTo.setOnClickListener {
            isFrom = false
            pickStation()
        }

    }


    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val station = data?.getSerializableExtra(Constant.STATION) as Station
                if (isFrom) {
                    binding.cityFrom.text = station.stationName
                    codeFrom = station.stationCode
                    viewModel.setStationFrom(station)
                } else {
                    binding.cityTo.text = station.stationName
                    codeTo = station.stationCode
                    viewModel.setStationTo(station)
                }
            }
        }

    private fun pickStation() {
        val intent = activity?.let { newIntent(it) }
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

            val pickDate = "$dayOfMonth.$month.$year"
            textView.text = pickDate
            viewModel.setDate(pickDate)

        }, mYear, mMonth, mDay)
        dpd.datePicker.minDate = c.time.time
        c.add(Calendar.MONTH, 3)
        dpd.datePicker.maxDate = c.time.time

        dpd.show()
    }

    private fun btnClickListener() {

        if (binding.cityFrom.text.isEmpty() || binding.cityTo.text.isEmpty() || binding.tvDate.text.isEmpty()) {
            Toast.makeText(activity, getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show()
            return
        }

        val bundle = Bundle()

        bundle.putString(Constant.SEARCH_CITY_FROM, "${binding.cityFrom.text}")
        bundle.putInt(Constant.SEARCH_CODE_FROM, codeFrom)
        bundle.putString(Constant.SEARCH_CITY_TO, "${binding.cityTo.text}")
        bundle.putInt(Constant.SEARCH_CODE_TO, codeTo)
        bundle.putString(Constant.SEARCH_DATE, "${binding.tvDate.text}")

        findNavController().navigate(R.id.action_searchFragment_to_searchResultFragment, bundle)
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, StationChoiseActivity::class.java)
        }
    }


}