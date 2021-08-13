package com.crostage.trainchecker.presentation.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.crostage.trainchecker.data.network.services.StationService
import com.crostage.trainchecker.data.repository.TrainDatabase
import com.crostage.trainchecker.data.repository.TrainRepository
import com.crostage.trainchecker.databinding.ActivityStationChoiseBinding
import com.crostage.trainchecker.domain.interactors.StationInteractor
import com.crostage.trainchecker.presentation.adapter.StationListAdapter
import com.crostage.trainchecker.presentation.viewmodel.StationViewModel
import com.crostage.trainchecker.presentation.viewmodel.factory.StationViewModelFactory
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import java.util.*
import java.util.concurrent.TimeUnit


class StationChoiseActivity : AppCompatActivity() {

    private lateinit var viewModel: StationViewModel
    private lateinit var adapter: StationListAdapter
    private lateinit var binding: ActivityStationChoiseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStationChoiseBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initRecyclerView()
        createViewModel()
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
                        if (text.length > 2) viewModel.getStation(text)
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
        adapter = StationListAdapter()
        binding.stationRecyclerview.adapter = adapter
    }

    private fun createViewModel() {
        val service = StationService()

        val db = TrainDatabase.invoke(this)
        val dao = db.trainDao()
        val repository = TrainRepository(dao)
        val interactor = StationInteractor(service, repository)
        val factory = StationViewModelFactory(interactor)
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
    }
}