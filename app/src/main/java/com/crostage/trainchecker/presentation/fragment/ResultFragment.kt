package com.crostage.trainchecker.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.crostage.trainchecker.R
import com.crostage.trainchecker.databinding.FragmentResultBinding
import com.crostage.trainchecker.domain.model.Train
import com.crostage.trainchecker.presentation.adapter.TrainListAdapter
import com.crostage.trainchecker.presentation.appComponent
import com.crostage.trainchecker.presentation.interfaces.FavouriteInsertListener
import com.crostage.trainchecker.presentation.interfaces.FavouriteRemoveListener
import com.crostage.trainchecker.presentation.interfaces.TrainItemClickListener
import com.crostage.trainchecker.presentation.util.Helper.Companion.showSnackBar
import com.crostage.trainchecker.presentation.viewmodel.TrainViewModel
import com.crostage.trainchecker.presentation.viewmodel.factory.TrainViewModelAssistedFactory
import javax.inject.Inject

class ResultFragment : Fragment(R.layout.fragment_result) {

    private lateinit var viewModel: TrainViewModel
    private lateinit var adapter: TrainListAdapter
    private lateinit var binding: FragmentResultBinding

    @Inject
    lateinit var assistedFactory: TrainViewModelAssistedFactory

    private val args: ResultFragmentArgs by navArgs()

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
        context.appComponent.getTrainComponent().build().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createViewModel()
        initRecyclerView()
        setObservers()
        setFromArguments()

    }

    private fun createViewModel() {
        val factory = assistedFactory.create(this)
        viewModel = ViewModelProvider(this, factory).get(TrainViewModel::class.java)
    }

    private fun setFromArguments() {

        val cityFrom = args.search.cityFrom
        val cityTo = args.search.cityTo
        val codeFrom = args.search.codeFrom
        val codeTo = args.search.codeTo
        val date = args.search.date

        binding.toolbarResult.apply {
            title =
                "$cityFrom -> $cityTo"
            setNavigationOnClickListener {
                activity?.onBackPressed()
            }
        }

        binding.tryAgain.setOnClickListener {
            viewModel.trainsFromSearchRequest(codeFrom, codeTo, date)
            binding.tryAgain.isVisible = false
        }

        if (viewModel.trains.value == null) {
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

        adapter = initAdapter()

        binding.resultRecyclerview.adapter = adapter
    }


    private fun initAdapter() =
        TrainListAdapter(object : FavouriteInsertListener {
            override fun addTrainToFavourite(train: Train) {
                viewModel.insertToFavourite(train)
                requireView()
                    .showSnackBar("Поезд ${train.trainNumber}  добавлен в отслеживаемые")
            }

        }, object : FavouriteRemoveListener {
            override fun removeTrainToFavourite(train: Train) {
                viewModel.removeFromFavourite(train)
                requireView()
                    .showSnackBar("Поезд ${train.trainNumber} удален из отслеживаемых")
            }
        },
            object : TrainItemClickListener {
                override fun trainClicked(train: Train) {
                    viewModel.trainClicked(train)
                }
            })


    private fun setObservers() {
        viewModel.error.observe(viewLifecycleOwner, {
            it.message?.let { msg -> requireView().showSnackBar(msg) }
            binding.tryAgain.isVisible = true
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

                val direction = ResultFragmentDirections
                    .actionResultFragmentToDetailFragment(train)

                findNavController().navigate(direction)
            }
        })
    }

}


