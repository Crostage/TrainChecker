package com.crostage.trainchecker.presentation.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.crostage.trainchecker.R
import com.crostage.trainchecker.databinding.FragmentSearchBinding
import com.crostage.trainchecker.domain.model.Station
import com.crostage.trainchecker.presentation.model.Search
import com.crostage.trainchecker.presentation.util.Helper.Companion.showSnackBar
import com.crostage.trainchecker.presentation.viewmodel.SearchViewModel
import com.crostage.trainchecker.utils.Constant
import com.crostage.trainchecker.utils.Constant.Companion.STATION_FROM_REQUEST_KEY
import com.crostage.trainchecker.utils.Constant.Companion.STATION_TO_REQUEST_KEY
import java.util.*


class SearchFragment : Fragment(R.layout.fragment_search) {

    private var codeFrom = 0
    private var codeTo = 0
    private lateinit var binding: FragmentSearchBinding
    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()

        initObserver()

        initClickListeners()

    }


    private fun initClickListeners() {
        binding.tvDate.setOnClickListener {
            datePick()
        }

        binding.btnSearch.setOnClickListener {
            btnSearchClick()
        }

        binding.cityFrom.setOnClickListener {
            pickStation(STATION_FROM_REQUEST_KEY)
        }
        binding.cityTo.setOnClickListener {
            pickStation(STATION_TO_REQUEST_KEY)
        }

        setFragmentResultListener(STATION_FROM_REQUEST_KEY) { _, bundle ->
            val station = bundle.getParcelable<Station>(Constant.STATION_RESULT)
            if (station != null) {
                binding.cityFrom.text = station.stationName
                codeFrom = station.stationCode
                viewModel.setStationFrom(station)
            }
        }

        setFragmentResultListener(STATION_TO_REQUEST_KEY) { _, bundle ->
            val station = bundle.getParcelable<Station>(Constant.STATION_RESULT)
            if (station != null) {
                binding.cityTo.text = station.stationName
                codeTo = station.stationCode
                viewModel.setStationTo(station)
            }
        }

    }

    private fun initViewModel() {

        viewModel.getStationFrom()?.let {
            codeFrom = it.stationCode
            binding.cityFrom.text = it.stationName
        }

        viewModel.getStationTo()?.let {
            codeTo = it.stationCode
            binding.cityTo.text = it.stationName
        }
    }

    private fun pickStation(requestKey: String) {
        val direction = SearchFragmentDirections.actionSearchFragmentToStationFragment(requestKey)
        findNavController().navigate(direction)
    }

    private fun datePick() {
        val c = Calendar.getInstance()
        val mYear = c.get(Calendar.YEAR)
        val mMonth = c.get(Calendar.MONTH)
        val mDay = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(requireActivity(), { _, year, monthOfYear, dayOfMonth ->

            val month = if (monthOfYear.toString().length == 1) {
                "0${monthOfYear + 1}"
            } else "${monthOfYear + 1}"


            val day = if (dayOfMonth.toString().length == 1) {
                "0$dayOfMonth"
            } else "$dayOfMonth"

            val pickDate = "$day.$month.$year"
            viewModel.setDate(pickDate)

        }, mYear, mMonth, mDay)

        dpd.datePicker.minDate = c.time.time
        c.add(Calendar.MONTH, 3)
        dpd.datePicker.maxDate = c.time.time
        dpd.show()
    }

    private fun initObserver() {
        viewModel.newDate.observe(viewLifecycleOwner, {
            binding.tvDate.text = it
        })
    }

    private fun btnSearchClick() {

        if (binding.cityFrom.text.isEmpty() || binding.cityTo.text.isEmpty() || binding.tvDate.text.isEmpty()) {

            requireView()
                .showSnackBar(getString(R.string.fill_all_fields))
            return
        }

        val search = Search(
            binding.cityFrom.text.toString(),
            codeFrom,
            binding.cityTo.text.toString(),
            codeTo,
            binding.tvDate.text.toString()
        )

        val direction = SearchFragmentDirections.actionSearchFragmentToResultFragment(search)
        findNavController().navigate(direction)
    }


}