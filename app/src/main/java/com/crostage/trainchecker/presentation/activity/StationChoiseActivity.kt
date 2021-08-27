package com.crostage.trainchecker.presentation.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.crostage.trainchecker.model.data.station.StationEntity
import com.crostage.trainchecker.databinding.ActivityStationChoiseBinding
import com.crostage.trainchecker.model.domain.Station
import com.crostage.trainchecker.presentation.adapter.StationListAdapter
import com.crostage.trainchecker.presentation.appComponent
import com.crostage.trainchecker.presentation.viewmodel.StationViewModel
import com.crostage.trainchecker.presentation.viewmodel.factory.StationViewModelFactory
import com.crostage.trainchecker.utils.Constant
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class StationChoiseActivity : AppCompatActivity(), OnStationClick {

    private lateinit var viewModel: StationViewModel
    private lateinit var adapter: StationListAdapter
    private lateinit var binding: ActivityStationChoiseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStationChoiseBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        appComponent.getStationComponent().inject(this)

        initRecyclerView()
        setObservers()
        initStationSearch()

    }

    private fun initStationSearch() {
        binding.stationName.apply {
            requestFocus()
            textChanges()
                .debounce(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    val text = it.toString().uppercase(Locale.getDefault()).trim()
                    if (text.length > 2) viewModel.getStationResponse(text)
                }
        }

    }

    private fun initRecyclerView() {

        binding.stationRecyclerview.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        binding.stationRecyclerview.addItemDecoration(
            DividerItemDecoration(
                binding.stationRecyclerview.context, LinearLayoutManager.VERTICAL
            )
        )
        adapter = StationListAdapter(this)
        binding.stationRecyclerview.adapter = adapter
    }

    @Inject
    fun createViewModel(factory: StationViewModelFactory) {
        viewModel = ViewModelProvider(this, factory).get(StationViewModel::class.java)

    }

    private fun setObservers() {
        viewModel.error.observe(this, {
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
        })

        viewModel.stations.observe(this, {
            if (it.isNotEmpty()) {
                adapter.setData(it)
            }
        })

        viewModel.progress.observe(this) { showProgress ->
            binding.progress.isVisible = showProgress
        }

        viewModel.getLastPickStations()
    }

//todo
override fun onStationClick(station: Station) {
    viewModel.insertStation(station)
    val intent = Intent()
    intent.putExtra(Constant.STATION, station)
    this.setResult(Activity.RESULT_OK, intent)
    this.finish()
}
}

interface OnStationClick {
    fun onStationClick(station: Station)
}