package com.crostage.trainchecker.presentation.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.crostage.trainchecker.R
import com.crostage.trainchecker.data.model.trainRequest.Train
import com.crostage.trainchecker.data.network.TrainServiceImp
import com.crostage.trainchecker.data.repository.TrainDao
import com.crostage.trainchecker.data.repository.TrainDatabase
import com.crostage.trainchecker.data.repository.TrainRepoImp
import com.crostage.trainchecker.presentation.adapter.RouteListAdapter
import com.crostage.trainchecker.utils.Constant
import com.crostage.trainchecker.presentation.viewmodel.RouteViewModel
import com.crostage.trainchecker.presentation.viewmodel.ViewModelFactory

class RoutesFragment : Fragment(R.layout.fragment_routes) {


    private lateinit var dao: TrainDao
    private lateinit var viewModel: RouteViewModel
    private lateinit var repository: TrainRepoImp
    private lateinit var responses: TrainServiceImp
    private lateinit var adapter: RouteListAdapter
    private lateinit var routeRecyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val trainNum = view.findViewById<TextView>(R.id.trainNumber)
        val trainFromTo = view.findViewById<TextView>(R.id.fromTo)
        val date = view.findViewById<TextView>(R.id.date)

        routeRecyclerView = view.findViewById<RecyclerView>(R.id.rvRoutesList)
        routeRecyclerView.layoutManager =
            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        adapter = RouteListAdapter()
        routeRecyclerView.adapter = adapter

        createViewModel()

        val train = arguments?.getSerializable(Constant.TRAIN_ROUTS) as Train

        train?.let {
            trainNum.text = train.trainNumber
            "${train.nameStationFrom} -> ${train.nameStationTo}".also { trainFromTo.text = it }
            date.text = train.dateStart
        }

        setObservers()

        if (savedInstanceState == null)
            viewModel.getRoutes(train)
    }


    private fun createViewModel() {
        dao = TrainDatabase.invoke(requireActivity()).trainDao()
        repository = TrainRepoImp(dao)
        responses = TrainServiceImp()
        viewModel = ViewModelFactory(repository, responses).create(RouteViewModel::class.java)
    }

    private fun setObservers() {
        viewModel.routes.observe(viewLifecycleOwner, {
            adapter.setData(it)
        })

        viewModel.error.observe(viewLifecycleOwner,{
            //todo toast error
        })
    }
}