package com.crostage.trainchecker

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.crostage.trainchecker.adapter.TrainListAdapter
import com.crostage.trainchecker.helper.Constant
import com.crostage.trainchecker.repository.TrainDao
import com.crostage.trainchecker.repository.TrainDatabase
import com.crostage.trainchecker.repository.TrainRepoImp
import com.crostage.trainchecker.viewmodel.TrainViewModel
import com.crostage.trainchecker.viewmodel.TrainViewModelFactory

class SearchResultFragment : Fragment(R.layout.fragment_result) {

    companion object {
        private const val TAG = "SearchResultFragment"
    }

    private lateinit var dao: TrainDao
    private lateinit var viewModel: TrainViewModel
    private lateinit var repository: TrainRepoImp


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rv = view.findViewById<RecyclerView>(R.id.recyclerTrainList)
        rv.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        val adapter = TrainListAdapter()
        rv.adapter = adapter


        dao = TrainDatabase.invoke(requireActivity()).trainDao()
        repository = TrainRepoImp(dao)
        viewModel = TrainViewModelFactory(repository).create(TrainViewModel::class.java)


        val cityFrom = arguments?.getString(Constant.SEARCH_CITY_FROM)
        val cityTo = arguments?.getString(Constant.SEARCH_CITY_TO)
        val date = arguments?.getString(Constant.SEARCH_DATE)

        if (cityFrom != null && cityTo != null && date != null)
            viewModel.getTrains(cityFrom, cityTo, date, adapter)

    }
}