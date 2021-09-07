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
import com.crostage.trainchecker.databinding.FragmentFavouriteBinding
import com.crostage.trainchecker.domain.model.Train
import com.crostage.trainchecker.presentation.adapter.TrainListAdapter
import com.crostage.trainchecker.presentation.appComponent
import com.crostage.trainchecker.presentation.viewmodel.TrainViewModel
import com.crostage.trainchecker.presentation.viewmodel.factory.TrainViewModelFactory
import com.crostage.trainchecker.utils.Constant
import com.crostage.trainchecker.utils.Helper
import javax.inject.Inject

class FavouriteFragment : Fragment(R.layout.fragment_favourite) {

    private lateinit var viewModel: TrainViewModel
    private lateinit var adapter: TrainListAdapter

    private lateinit var binding: FragmentFavouriteBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.getFavouriteComponent().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        setObservers()
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

    //todo убрать одинаковый код
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

    @Inject
    fun initViewModel(factory: TrainViewModelFactory) {
        viewModel = ViewModelProvider(this, factory).get(TrainViewModel::class.java)
    }

    private fun setObservers() {
        viewModel.error.observe(viewLifecycleOwner, {
            it.message?.let { msg -> Helper.showNewSnack(requireView(), msg) }
        })

        viewModel.getFavouriteTrainList().observe(viewLifecycleOwner, {

            if (it != null) {
                val actualList = viewModel.chekFavouritesOnActualDate(it)
                adapter.setData(actualList)
                binding.listIsEmpty.isVisible = actualList.isEmpty()

            }
        })

        viewModel.openDetail.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()?.let { train ->
                val bundle = Bundle()
                bundle.putParcelable(Constant.TRAIN_ARG, train)
                NavHostFragment.findNavController(this)
                    .navigate(R.id.action_favouriteFragment_to_detailFragment, bundle)
            }

        })

    }

}