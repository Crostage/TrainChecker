package com.crostage.trainchecker.presentation

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
import com.crostage.trainchecker.domain.adapter.RouteListAdapter
import com.crostage.trainchecker.domain.adapter.TrainListAdapter
import com.crostage.trainchecker.helper.Constant
import com.crostage.trainchecker.viewmodel.TrainViewModel
import com.crostage.trainchecker.viewmodel.TrainViewModelFactory

class RoutesFragment: Fragment(R.layout.fragment_routes) {


    private lateinit var dao: TrainDao
    private lateinit var viewModel: TrainViewModel
    private lateinit var repository: TrainRepoImp
    private lateinit var responses: TrainServiceImp

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val trainNum = view.findViewById<TextView>(R.id.trainNumber)
        val trainFromTo = view.findViewById<TextView>(R.id.fromTo)
        val date = view.findViewById<TextView>(R.id.date)


        val rv = view.findViewById<RecyclerView>(R.id.rvRoutesList)
        rv.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        val adapter = RouteListAdapter()
        rv.adapter = adapter

        dao = TrainDatabase.invoke(requireActivity()).trainDao()
        repository = TrainRepoImp(dao)
        responses = TrainServiceImp()
        viewModel = TrainViewModelFactory(repository, responses).create(TrainViewModel::class.java)


        val train = arguments?.getSerializable(Constant.TRAIN_ROUTS) as Train

        train?.let {
            trainNum.text = train.number
            trainFromTo.text = "${train.route0} -> ${train.route1}"
            date.text = train.date0
        }


        viewModel.routes.observe(viewLifecycleOwner, {
            adapter.setData(it)
        })


        viewModel.getRoutes(train)
    }
}