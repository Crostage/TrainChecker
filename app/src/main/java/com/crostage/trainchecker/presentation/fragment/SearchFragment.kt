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
import com.crostage.trainchecker.utils.Constant.Companion.STATION_FROM_REQUEST_KEY
import com.crostage.trainchecker.utils.Constant.Companion.STATION_RESULT
import com.crostage.trainchecker.utils.Constant.Companion.STATION_TO_REQUEST_KEY
import java.util.*


class SearchFragment : Fragment(R.layout.fragment_search) {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel: SearchViewModel by viewModels()


    private var stationFrom: Station? = null
    private var stationTo: Station? = null
    private var date: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initObserver()

        initListeners()

    }


    private fun initListeners() {
        binding.tvDate.setOnClickListener {
            datePick()
        }

        binding.btnSearch.setOnClickListener {
            btnSearchClick()
        }

        binding.stationFrom.setOnClickListener {
            pickStation(STATION_FROM_REQUEST_KEY)
        }
        binding.stationTo.setOnClickListener {
            pickStation(STATION_TO_REQUEST_KEY)
        }

        setFragmentResultListener(STATION_FROM_REQUEST_KEY) { _, bundle ->
            val station = bundle.getParcelable<Station>(STATION_RESULT)
            if (station != null) {

                viewModel.setStationFrom(station)
            }
        }

        setFragmentResultListener(STATION_TO_REQUEST_KEY) { _, bundle ->
            val station = bundle.getParcelable<Station>(STATION_RESULT)
            if (station != null) {

                viewModel.setStationTo(station)
            }
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
            date = it
        })
        viewModel.stationTo.observe(viewLifecycleOwner, {
            if (it != null) {
                binding.stationTo.text = it.stationName
                stationTo = it
            }
        })
        viewModel.stationFrom.observe(viewLifecycleOwner, {
            if (it != null) {
                binding.stationFrom.text = it.stationName
                stationFrom = it
            }
        })
    }

    private fun btnSearchClick() {

        if (stationFrom != null
            && stationTo != null
            && date != null
        ) {
            val search = Search(
                stationFrom!!.stationName,
                stationFrom!!.stationCode,
                stationTo!!.stationName,
                stationTo!!.stationCode,
                date!!
            )
            val direction = SearchFragmentDirections.actionSearchFragmentToResultFragment(search)
            findNavController().navigate(direction)
        } else {
            requireView()
                .showSnackBar(getString(R.string.fill_all_fields))

        }
    }


}