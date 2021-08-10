package com.crostage.trainchecker.presentation.fragments

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.crostage.trainchecker.R
import com.crostage.trainchecker.data.network.TrainServiceImp
import com.crostage.trainchecker.presentation.adapter.TrainListAdapter
import com.crostage.trainchecker.utils.Constant
import com.crostage.trainchecker.data.repository.TrainDao
import com.crostage.trainchecker.data.repository.TrainDatabase
import com.crostage.trainchecker.data.repository.TrainRepoImp
import com.crostage.trainchecker.presentation.viewmodel.TrainViewModel
import com.crostage.trainchecker.presentation.viewmodel.ViewModelFactory
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
    private lateinit var adapter: TrainListAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rv = view.findViewById<RecyclerView>(R.id.recyclerTrainList)
        rv.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        adapter = TrainListAdapter()
        rv.adapter = adapter
        progress = view.findViewById(R.id.progressResult)


        val cityFrom = arguments?.getString(Constant.SEARCH_CITY_FROM)
        val cityTo = arguments?.getString(Constant.SEARCH_CITY_TO)
        val date = arguments?.getString(Constant.SEARCH_DATE)

        createViewModel()

        setObservers()

        if (cityFrom != null && cityTo != null && date != null) {
            activity?.title =
                "${cityFrom.uppercase(Locale.getDefault())} -> ${cityTo.uppercase(Locale.getDefault())}  $date"

            viewModel.trainsFromSearchRequest(cityFrom, cityTo, date)
            progress.visibility = View.VISIBLE
        }

    }

    private fun createViewModel() {
        dao = TrainDatabase.invoke(requireActivity()).trainDao()
        repository = TrainRepoImp(dao)
        responses = TrainServiceImp()
        val factory = ViewModelFactory(repository, responses)
        viewModel = ViewModelProvider(this, factory).get(TrainViewModel::class.java)
    }

    private fun setObservers() {
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
    }

}