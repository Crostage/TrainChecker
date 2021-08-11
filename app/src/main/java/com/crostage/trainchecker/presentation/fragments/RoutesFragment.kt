package com.crostage.trainchecker.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.crostage.trainchecker.R
import com.crostage.trainchecker.data.model.trainRequest.Train
import com.crostage.trainchecker.data.network.TrainService
import com.crostage.trainchecker.data.repository.TrainDatabase
import com.crostage.trainchecker.data.repository.TrainRepoImp
import com.crostage.trainchecker.databinding.FragmentRoutesBinding
import com.crostage.trainchecker.presentation.adapter.RouteListAdapter
import com.crostage.trainchecker.presentation.viewmodel.RouteViewModel
import com.crostage.trainchecker.presentation.viewmodel.ViewModelFactory
import com.crostage.trainchecker.utils.Constant

class RoutesFragment : Fragment(R.layout.fragment_routes) {


    private lateinit var viewModel: RouteViewModel
    private lateinit var adapter: RouteListAdapter
    private lateinit var binding: FragmentRoutesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRoutesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initRecyclerview()
        createViewModel()

        val train = arguments?.getSerializable(Constant.TRAIN_ROUTS) as Train?

        train?.let {
            binding.trainNumber.text = train.trainNumber
            "${train.nameStationFrom} -> ${train.nameStationTo}".also { binding.fromTo.text = it }
            binding.date.text = train.dateStart

            setObservers()

            if (savedInstanceState == null)
                viewModel.getRoutes(train)
        }

    }

    private fun initRecyclerview() {
        binding.routeRecyclerview.layoutManager =
            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        adapter = RouteListAdapter()
        binding.routeRecyclerview.adapter = adapter
    }

    private fun createViewModel() {
        val dao = TrainDatabase.invoke(requireActivity()).trainDao()
        val repository = TrainRepoImp(dao)
        val responses = TrainService()
        val factory = ViewModelFactory(repository, responses)
        viewModel = ViewModelProvider(this, factory).get(RouteViewModel::class.java)
    }

    private fun setObservers() {
        viewModel.routes.observe(viewLifecycleOwner, {
            adapter.setData(it)
        })

        viewModel.error.observe(viewLifecycleOwner, {
            Toast.makeText(activity, it.message, Toast.LENGTH_SHORT)
                .show()
        })
        viewModel.progress.observe(viewLifecycleOwner) { showProgress ->
            binding.progress.isVisible = showProgress
        }
    }
}