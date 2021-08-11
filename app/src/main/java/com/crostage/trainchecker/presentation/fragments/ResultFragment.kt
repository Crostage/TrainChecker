package com.crostage.trainchecker.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.crostage.trainchecker.R
import com.crostage.trainchecker.data.network.TrainService
import com.crostage.trainchecker.data.repository.TrainDatabase
import com.crostage.trainchecker.data.repository.TrainRepoImp
import com.crostage.trainchecker.databinding.FragmentResultBinding
import com.crostage.trainchecker.presentation.adapter.TrainListAdapter
import com.crostage.trainchecker.presentation.viewmodel.TrainViewModel
import com.crostage.trainchecker.presentation.viewmodel.ViewModelFactory
import com.crostage.trainchecker.utils.Constant
import java.util.*

class ResultFragment : Fragment(R.layout.fragment_result) {

    companion object {
        private const val TAG = "SearchResultFragment"
    }

    private lateinit var viewModel: TrainViewModel
    private lateinit var adapter: TrainListAdapter

    private lateinit var binding: FragmentResultBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initRecyclerView()

        val cityFrom = arguments?.getString(Constant.SEARCH_CITY_FROM)
        val cityTo = arguments?.getString(Constant.SEARCH_CITY_TO)
        val date = arguments?.getString(Constant.SEARCH_DATE)

        createViewModel()

        setObservers()

        if (cityFrom != null && cityTo != null && date != null) {
            activity?.title =
                "${cityFrom.uppercase(Locale.getDefault())} -> ${cityTo.uppercase(Locale.getDefault())}  $date"

            if (savedInstanceState == null)
                viewModel.trainsFromSearchRequest(cityFrom, cityTo, date)
        }

    }

    private fun initRecyclerView() {

        binding.resultRecyclerview.layoutManager =
            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

        binding.resultRecyclerview.addItemDecoration(
            DividerItemDecoration(
                binding.resultRecyclerview.context,
                LinearLayoutManager.VERTICAL
            )
        )
        adapter = TrainListAdapter()
        binding.resultRecyclerview.adapter = adapter
    }

    private fun createViewModel() {
        val dao = TrainDatabase.invoke(requireActivity()).trainDao()
        val repository = TrainRepoImp(dao)
        val responses = TrainService()
        val factory = ViewModelFactory(repository, responses)
        viewModel = ViewModelProvider(this, factory).get(TrainViewModel::class.java)
    }

    private fun setObservers() {
        viewModel.error.observe(viewLifecycleOwner, {
            Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
        })

        viewModel.trains.observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                adapter.setData(it)
            }
        })

        viewModel.progress.observe(viewLifecycleOwner) { showProgress ->
            binding.progress.isVisible = showProgress
        }
    }

}