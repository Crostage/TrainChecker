package com.crostage.trainchecker.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.crostage.trainchecker.R
import com.crostage.trainchecker.databinding.FragmentResultBinding
import com.crostage.trainchecker.domain.model.Train
import com.crostage.trainchecker.presentation.adapter.TrainListAdapter
import com.crostage.trainchecker.presentation.appComponent
import com.crostage.trainchecker.presentation.viewmodel.TrainViewModel
import com.crostage.trainchecker.presentation.viewmodel.factory.TrainViewModelFactory
import com.crostage.trainchecker.utils.Constant
import com.crostage.trainchecker.presentation.util.Helper
import java.util.*
import javax.inject.Inject

class ResultFragment : Fragment(R.layout.fragment_result) {

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
        setObservers()
        setFromArguments(arguments)

    }


    @Inject
    fun createViewModel(factory: TrainViewModelFactory) {
        viewModel = ViewModelProvider(this, factory).get(TrainViewModel::class.java)
    }


    private fun setFromArguments(arguments: Bundle?) {
        val cityFrom = arguments?.getString(Constant.SEARCH_CITY_FROM)
        val codeFrom = arguments?.getInt(Constant.SEARCH_CODE_FROM)
        val cityTo = arguments?.getString(Constant.SEARCH_CITY_TO)
        val codeTo = arguments?.getInt(Constant.SEARCH_CODE_TO)
        val date = arguments?.getString(Constant.SEARCH_DATE)

        if (cityFrom != null && cityTo != null
            && date != null && codeFrom != null && codeTo != null
        ) {

            binding.toolbarResult.apply {
                title =
                    "${cityFrom.uppercase(Locale.getDefault())} " +
                            "-> ${cityTo.uppercase(Locale.getDefault())}  $date"

                setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
                setNavigationOnClickListener {
                    activity?.onBackPressed()
                }
            }


            if (viewModel.trains.value == null) {
                viewModel.trainsFromSearchRequest(codeFrom, codeTo, date)
            }
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

        adapter = initAdapter()

        binding.resultRecyclerview.adapter = adapter
    }


    private fun initAdapter() =
        TrainListAdapter(object : FavouriteClickListener {
            override fun addTrainToFavourite(train: Train) {
                viewModel.insertToFavourite(train)
                Helper.showNewSnack(
                    requireView(),
                    "Поезд ${train.trainNumber}  добавлен в отслеживаемые"
                )
            }

            override fun removeTrainToFavourite(train: Train) {
                viewModel.removeFromFavourite(train)
                Helper.showNewSnack(
                    requireView(),
                    "Поезд ${train.trainNumber} удален из отслеживаемых"
                )
            }

        },
            object : TrainItemClickListener {
                override fun trainClicked(train: Train) {
                    viewModel.trainClicked(train)
                }
            })


    private fun setObservers() {
        viewModel.error.observe(viewLifecycleOwner, {
            it.message?.let { it1 -> Helper.showNewSnack(requireView(), it1) }
        })

        viewModel.trains.observe(viewLifecycleOwner, {
            adapter.setData(it)
            binding.listIsEmpty.isVisible = it.isEmpty()
        })

        viewModel.progress.observe(viewLifecycleOwner) { showProgress ->
            binding.progress.isVisible = showProgress
        }

        viewModel.getFavouriteLiveData().observe(viewLifecycleOwner, {
            viewModel.checkFavouritesContainsTrains(it)
        })

        viewModel.openDetail.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()?.let { train ->
                val bundle = Bundle()
                bundle.putParcelable(Constant.TRAIN_ARG, train)
                NavHostFragment.findNavController(this)
                    .navigate(R.id.action_searchResultFragment_to_detailFragment, bundle)
            }
        })
    }

}

interface FavouriteClickListener {
    fun addTrainToFavourite(train: Train)

    fun removeTrainToFavourite(train: Train)
}

interface TrainItemClickListener {
    fun trainClicked(train: Train)
}