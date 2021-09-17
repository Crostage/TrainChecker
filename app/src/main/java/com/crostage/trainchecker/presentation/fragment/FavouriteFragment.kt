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
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.crostage.trainchecker.R
import com.crostage.trainchecker.databinding.FragmentFavouriteBinding
import com.crostage.trainchecker.domain.model.Train
import com.crostage.trainchecker.presentation.adapter.FavouriteAdapter
import com.crostage.trainchecker.presentation.appComponent
import com.crostage.trainchecker.presentation.interfaces.FavouriteRemoveListener
import com.crostage.trainchecker.presentation.interfaces.TrainItemClickListener
import com.crostage.trainchecker.presentation.util.Helper.Companion.showSnackBar
import com.crostage.trainchecker.presentation.viewmodel.FavouriteViewModel
import com.crostage.trainchecker.presentation.viewmodel.factory.FavouriteViewModelFactory
import javax.inject.Inject

/**
 * Фрагмент отображающий список отслеживаемых поездов
 *
 */
class FavouriteFragment : Fragment(R.layout.fragment_favourite) {

    private lateinit var viewModel: FavouriteViewModel
    private lateinit var adapter: FavouriteAdapter
    private var _binding: FragmentFavouriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.getFavouriteComponent().build().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        setObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
        FavouriteAdapter(object : FavouriteRemoveListener {

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

    @Inject
    fun createViewModel(factory: FavouriteViewModelFactory) {
        viewModel = ViewModelProvider(this, factory).get(FavouriteViewModel::class.java)
    }

    private fun setObservers() {
        viewModel.error.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()?.message?.let { msg ->
                requireActivity().findViewById<View>(android.R.id.content).showSnackBar(msg)
            }
        })

        viewModel.getFavouriteLiveData().observe(viewLifecycleOwner, {

            if (it != null) {
                val actualList = viewModel.chekFavouritesOnActualDate(it)
                adapter.setData(actualList)
                binding.listIsEmpty.isVisible = actualList.isEmpty()

            }
        })

        viewModel.openDetail.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()?.let { train ->

                val direction = FavouriteFragmentDirections
                    .actionFavouriteFragmentToDetailFavouriteFragment(train)

                findNavController().navigate(direction)
            }

        })

    }

}