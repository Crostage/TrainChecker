package com.crostage.trainchecker.presentation

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.crostage.trainchecker.R
import com.crostage.trainchecker.data.network.TrainServiceImp
import com.crostage.trainchecker.domain.adapter.TrainListAdapter
import com.crostage.trainchecker.helper.Constant
import com.crostage.trainchecker.data.repository.TrainDao
import com.crostage.trainchecker.data.repository.TrainDatabase
import com.crostage.trainchecker.data.repository.TrainRepoImp
import com.crostage.trainchecker.viewmodel.TrainViewModel
import com.crostage.trainchecker.viewmodel.TrainViewModelFactory
import java.util.*

class SearchResultFragment : Fragment(R.layout.fragment_result) {

    companion object {
        private const val TAG = "SearchResultFragment"
    }

    private lateinit var dao: TrainDao
    private lateinit var viewModel: TrainViewModel
    private lateinit var repository: TrainRepoImp
    private lateinit var responses: TrainServiceImp
    private lateinit var progress: ProgressBar


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rv = view.findViewById<RecyclerView>(R.id.recyclerTrainList)
        rv.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        val adapter = TrainListAdapter()
        rv.adapter = adapter
        progress = view.findViewById(R.id.progressResult)

        dao = TrainDatabase.invoke(requireActivity()).trainDao()
        repository = TrainRepoImp(dao)
        responses = TrainServiceImp()
        viewModel = TrainViewModelFactory(repository, responses).create(TrainViewModel::class.java)

        val cityFrom = arguments?.getString(Constant.SEARCH_CITY_FROM)
        val cityTo = arguments?.getString(Constant.SEARCH_CITY_TO)
        val date = arguments?.getString(Constant.SEARCH_DATE)

        viewModel.error.observe(viewLifecycleOwner, {
            Toast.makeText(activity, "Connection problem", Toast.LENGTH_SHORT).show()
            progress.visibility = View.GONE
        })

        viewModel.trains.observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                adapter.setData(it)
                progress.visibility = View.GONE
            }
        })

        if (cityFrom != null && cityTo != null && date != null) {
            activity?.title =
                "${cityFrom.uppercase(Locale.getDefault())} -> ${cityTo.uppercase(Locale.getDefault())}  $date"
            viewModel.getTrains(cityFrom, cityTo, date)
            progress.visibility = View.VISIBLE
        }

    }
}