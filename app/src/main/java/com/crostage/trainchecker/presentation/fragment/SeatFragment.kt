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
import com.crostage.trainchecker.databinding.FragmentSeatBinding
import com.crostage.trainchecker.data.model.train.Train
import com.crostage.trainchecker.presentation.adapter.SeatListAdapter
import com.crostage.trainchecker.presentation.appComponent
import com.crostage.trainchecker.presentation.viewmodel.SeatViewModel
import com.crostage.trainchecker.presentation.viewmodel.factory.SeatViewModelFactory
import com.crostage.trainchecker.utils.Constant
import javax.inject.Inject


class SeatFragment : Fragment(R.layout.fragment_seat) {
    private lateinit var viewModel: SeatViewModel
    private lateinit var adapter: SeatListAdapter
    private lateinit var binding: FragmentSeatBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.getSeatComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSeatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerview()

        val train = arguments?.getSerializable(Constant.TRAIN_ARG) as Train?

        train?.let {

            setObservers()

            if (viewModel.cars.value == null)
                viewModel.getCarList(train)
        }

    }

    private fun initRecyclerview() {
        binding.seatRecyclerview.layoutManager =
            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        adapter = SeatListAdapter()
        binding.seatRecyclerview.addItemDecoration(
            DividerItemDecoration(
                binding.seatRecyclerview.context,
                LinearLayoutManager.VERTICAL
            )
        )
        binding.seatRecyclerview.adapter = adapter
    }

    @Inject
    fun createViewModel(factory: SeatViewModelFactory) {
        viewModel = ViewModelProvider(this, factory).get(SeatViewModel::class.java)
    }

    private fun setObservers() {
        viewModel.cars.observe(viewLifecycleOwner, {
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