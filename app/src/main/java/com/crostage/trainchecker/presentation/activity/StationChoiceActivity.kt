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
import com.crostage.trainchecker.databinding.ActivityStationChoiceBinding
import com.crostage.trainchecker.domain.model.Station
import com.crostage.trainchecker.presentation.adapter.StationListAdapter
import com.crostage.trainchecker.presentation.appComponent
import com.crostage.trainchecker.presentation.util.Helper
import com.crostage.trainchecker.presentation.util.Helper.Companion.showSnackBar
import com.crostage.trainchecker.presentation.viewmodel.StationViewModel
import com.crostage.trainchecker.presentation.viewmodel.factory.StationViewModelFactory
import com.crostage.trainchecker.utils.Constant
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class StationChoiceActivity : AppCompatActivity() {

    private lateinit var adapter: StationListAdapter
    private lateinit var binding: ActivityStationChoiceBinding

    private lateinit var viewModel: StationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStationChoiceBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        appComponent.getStationComponent().inject(this)

        initRecyclerView()
        setObservers()
        initStationSearch()
    }

    private fun initStationSearch() {
        //todo как-то убрать в вм
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
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)

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
        viewModel.error.observe(this, {
            it.message?.let { msg -> binding.stationRecyclerview.showSnackBar(msg) }
        })

        viewModel.stations.observe(this, {
            binding.listIsEmpty.isVisible = it.isEmpty()
            adapter.setData(it)
        })

        viewModel.progress.observe(this) { showProgress ->
            binding.progress.isVisible = showProgress
        }

        viewModel.resultStation.observe(this) { event ->
            event.getContentIfNotHandled()?.let {
                val intent = Intent()
                intent.putExtra(Constant.STATION, it)
                this.setResult(Activity.RESULT_OK, intent)
                this.finish()
            }
        }

    }

}

interface OnStationClick {
    fun onStationClick(station: Station)
}