package com.crostage.trainchecker.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.crostage.trainchecker.R
import com.crostage.trainchecker.databinding.FragmentStationBinding
import com.crostage.trainchecker.domain.model.Station
import com.crostage.trainchecker.presentation.adapter.StationListAdapter
import com.crostage.trainchecker.presentation.appComponent
import com.crostage.trainchecker.presentation.util.Helper.Companion.showSnackBar
import com.crostage.trainchecker.presentation.viewmodel.StationViewModel
import com.crostage.trainchecker.presentation.viewmodel.factory.StationViewModelFactory
import com.crostage.trainchecker.utils.Constant.Companion.STATION_RESULT
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class StationFragment : Fragment(R.layout.fragment_station) {
    private lateinit var adapter: StationListAdapter
    private lateinit var binding: FragmentStationBinding
    private lateinit var viewModel: StationViewModel

    private val args: StationFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.getStationComponent().build().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentStationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        setObservers()
        initStationSearch()
    }

    private fun initStationSearch() {
        binding.stationName.apply {
            textChanges()
                .debounce(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    val text = it.toString().uppercase(Locale.getDefault()).trim()
                    if (text.length > 2) viewModel.getStationResponse(text)
                }
        }

    }

    @Inject
    fun createViewModel(factory: StationViewModelFactory) {
        viewModel = ViewModelProvider(this, factory).get(StationViewModel::class.java)
    }


    private fun initRecyclerView() {

        binding.stationRecyclerview.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        binding.stationRecyclerview.addItemDecoration(
            DividerItemDecoration(
                binding.stationRecyclerview.context, LinearLayoutManager.VERTICAL
            )
        )

        adapter = StationListAdapter(object : OnStationClick {
            override fun onStationClick(station: Station) {
                viewModel.setResultStation(station)
            }
        })

        binding.stationRecyclerview.adapter = adapter
    }


    private fun setObservers() {
        viewModel.error.observe(viewLifecycleOwner, {
            it.message?.let { msg -> binding.stationRecyclerview.showSnackBar(msg) }
        })

        viewModel.stations.observe(viewLifecycleOwner, {
            binding.listIsEmpty.isVisible = it.isEmpty()
            adapter.setData(it)
        })

        viewModel.progress.observe(viewLifecycleOwner) { showProgress ->
            binding.progress.isVisible = showProgress
        }

        viewModel.resultStation.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                setFragmentResult(args.requestKey, bundleOf(STATION_RESULT to it))
                findNavController().popBackStack()
            }
        }

    }

}

interface OnStationClick {
    fun onStationClick(station: Station)
}