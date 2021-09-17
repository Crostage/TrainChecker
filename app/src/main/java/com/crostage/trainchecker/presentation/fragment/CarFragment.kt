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
import com.crostage.trainchecker.databinding.FragmentCarBinding
import com.crostage.trainchecker.domain.model.Train
import com.crostage.trainchecker.presentation.adapter.CarListAdapter
import com.crostage.trainchecker.presentation.appComponent
import com.crostage.trainchecker.presentation.util.Helper.Companion.showSnackBar
import com.crostage.trainchecker.presentation.viewmodel.CarViewModel
import com.crostage.trainchecker.presentation.viewmodel.factory.SeatViewModelAssistedFactory
import com.crostage.trainchecker.utils.Constant
import javax.inject.Inject


/**
 * Фрагмент отображающий список вагонов с информацией
 *
 */
class CarFragment : Fragment(R.layout.fragment_car) {
    private lateinit var viewModel: CarViewModel
    private lateinit var adapter: CarListAdapter
    private var _binding: FragmentCarBinding? = null
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
        _binding = FragmentCarBinding.inflate(inflater, container, false)
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
        adapter = CarListAdapter()
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
        viewModel = ViewModelProvider(this, factory).get(CarViewModel::class.java)
    }

    private fun setObservers() {
        viewModel.cars.observe(viewLifecycleOwner, {
            adapter.setData(it)
            binding.listIsEmpty.isVisible = it.isEmpty()
        })

        viewModel.error.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()?.message?.let { msg ->
                requireActivity().findViewById<View>(android.R.id.content).showSnackBar(msg)
            }
            binding.tryAgain.isVisible = true
        })
        viewModel.progress.observe(viewLifecycleOwner) { showProgress ->
            binding.progress.isVisible = showProgress
        }
    }
}