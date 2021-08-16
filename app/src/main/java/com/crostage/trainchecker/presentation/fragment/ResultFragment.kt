package com.crostage.trainchecker.presentation.fragment

import android.content.Context
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
import com.crostage.trainchecker.databinding.FragmentResultBinding
import com.crostage.trainchecker.presentation.adapter.TrainListAdapter
import com.crostage.trainchecker.presentation.appComponent
import com.crostage.trainchecker.presentation.viewmodel.TrainViewModel
import com.crostage.trainchecker.presentation.viewmodel.factory.TrainViewModelFactory
import com.crostage.trainchecker.utils.Constant
import java.util.*
import javax.inject.Inject

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
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.getTrainComponent().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        val cityFrom = arguments?.getString(Constant.SEARCH_CITY_FROM)
        val codeFrom = arguments?.getInt(Constant.SEARCH_CODE_FROM)
        val cityTo = arguments?.getString(Constant.SEARCH_CITY_TO)
        val codeTo = arguments?.getInt(Constant.SEARCH_CODE_TO)
        val date = arguments?.getString(Constant.SEARCH_DATE)

        setObservers()

        if (cityFrom != null && cityTo != null && date != null && codeFrom != null && codeTo != null) {
            activity?.title =
                "${cityFrom.uppercase(Locale.getDefault())} -> ${cityTo.uppercase(Locale.getDefault())}  $date"

            if (viewModel.trains.value == null)
                viewModel.trainsFromSearchRequest(codeFrom, codeTo, date)
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

    @Inject
    fun createViewModel(factory: TrainViewModelFactory) {
        viewModel = ViewModelProvider(this, factory).get(TrainViewModel::class.java)
    }

    private fun setObservers() {
        viewModel.error.observe(viewLifecycleOwner, {
            Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
        })

        viewModel.trains.observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                adapter.setData(it)
                binding.listIsEmpty.isVisible = false
            } else {
                binding.listIsEmpty.isVisible = true
            }
        })

        viewModel.progress.observe(viewLifecycleOwner) { showProgress ->
            binding.progress.isVisible = showProgress
        }
    }

}