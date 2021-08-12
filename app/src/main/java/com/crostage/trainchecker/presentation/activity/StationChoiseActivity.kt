package com.crostage.trainchecker.presentation.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.crostage.trainchecker.data.network.services.TrainService
import com.crostage.trainchecker.data.repository.TrainDatabase
import com.crostage.trainchecker.data.repository.TrainRepoImp
import com.crostage.trainchecker.databinding.ActivityStationChoiseBinding
import com.crostage.trainchecker.presentation.adapter.StationListAdapter
import com.crostage.trainchecker.presentation.viewmodel.StationViewModel
import com.crostage.trainchecker.presentation.viewmodel.ViewModelFactory
import java.util.*


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

        binding.stationName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val text = s.toString().uppercase(Locale.getDefault()).trim()
                if (text.length > 2) viewModel.getStation(text)
            }

        })

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
        val dao = TrainDatabase.invoke(this).trainDao()
        val repository = TrainRepoImp(dao)
        val responses = TrainService()
        val factory = ViewModelFactory(repository, responses)
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