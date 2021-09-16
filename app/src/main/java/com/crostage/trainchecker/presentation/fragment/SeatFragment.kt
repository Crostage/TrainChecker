package com.crostage.trainchecker.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.crostage.trainchecker.R
import com.crostage.trainchecker.databinding.FragmentSeatBinding
import com.crostage.trainchecker.domain.model.Train
import com.crostage.trainchecker.presentation.adapter.SeatListAdapter
import com.crostage.trainchecker.presentation.appComponent
import com.crostage.trainchecker.presentation.util.Helper.Companion.showSnackBar
import com.crostage.trainchecker.presentation.viewmodel.SeatViewModel
import com.crostage.trainchecker.presentation.viewmodel.factory.SeatViewModelAssistedFactory
import com.crostage.trainchecker.utils.Constant
import javax.inject.Inject


class SeatFragment : Fragment(R.layout.fragment_seat) {
    private lateinit var viewModel: SeatViewModel
    private lateinit var adapter: SeatListAdapter
    private var _binding: FragmentSeatBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var assistedFactory: SeatViewModelAssistedFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.getSeatComponent().build().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSeatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createViewModel()
        initRecyclerview()
        setFromArguments(arguments)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setFromArguments(arguments: Bundle?) {
        val train = arguments?.getParcelable<Train>(Constant.TRAIN_ARG)

        train?.let {

            setObservers()

            binding.tryAgain.setOnClickListener {
                viewModel.getCarList(train)
                binding.tryAgain.isVisible = false
            }

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

    private fun createViewModel() {
        val factory = assistedFactory.create(this)
        viewModel = ViewModelProvider(this, factory).get(SeatViewModel::class.java)
    }

    private fun setObservers() {
        viewModel.cars.observe(viewLifecycleOwner, {
            adapter.setData(it)
            binding.listIsEmpty.isVisible = it.isEmpty()
        })

        viewModel.error.observe(viewLifecycleOwner, {
            it.message?.let { msg -> requireView().showSnackBar(msg) }
            binding.tryAgain.isVisible = true
        })
        viewModel.progress.observe(viewLifecycleOwner) { showProgress ->
            binding.progress.isVisible = showProgress
        }
    }
}