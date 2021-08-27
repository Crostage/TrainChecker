package com.crostage.trainchecker.presentation.fragment

import android.annotation.SuppressLint
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
import com.crostage.trainchecker.data.converter.TrainConverter
import com.crostage.trainchecker.databinding.FragmentFavouriteBinding
import com.crostage.trainchecker.model.data.train.TrainEntity
import com.crostage.trainchecker.model.domain.Train
import com.crostage.trainchecker.presentation.adapter.TrainListAdapter
import com.crostage.trainchecker.presentation.appComponent
import com.crostage.trainchecker.presentation.viewmodel.TrainViewModel
import com.crostage.trainchecker.presentation.viewmodel.factory.TrainViewModelFactory
import com.crostage.trainchecker.utils.Helper
import javax.inject.Inject

class FavouriteFragment : Fragment(R.layout.fragment_favourite), FavouriteClickListener {

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
        adapter = TrainListAdapter(this)
        binding.resultRecyclerview.adapter = adapter

    }

    @Inject
    fun initViewModel(factory: TrainViewModelFactory) {
        viewModel = ViewModelProvider(this, factory).get(TrainViewModel::class.java)
    }

    @SuppressLint("SimpleDateFormat")
    private fun setObservers() {
        viewModel.error.observe(viewLifecycleOwner, {
            Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
        })

        viewModel.getFavouriteTrainList().observe(viewLifecycleOwner, {

            if (it != null) {

                val actualList = Helper.checkFavouriteOnActualDate(it)
                val removeList = it.toMutableList()
                removeList.removeAll(actualList)
                removeList.forEach { train -> removeTrainToFavourite(train) }

                adapter.setFavouriteData(actualList)
                adapter.setData(actualList)

                binding.listIsEmpty.isVisible = actualList.isEmpty()
            }
        })

    }

    override fun addTrainToFavourite(train: Train) {
    }

    override fun removeTrainToFavourite(train: Train) {
        viewModel.removeFromFavourite(train)
        Toast.makeText(context,
            "Поезд ${train.trainNumber} удален из отслеживаемых",
            Toast.LENGTH_SHORT).show()
    }
}