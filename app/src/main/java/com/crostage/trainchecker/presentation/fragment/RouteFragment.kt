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
import com.crostage.trainchecker.databinding.FragmentRouteBinding
import com.crostage.trainchecker.domain.model.Train
import com.crostage.trainchecker.presentation.adapter.RouteListAdapter
import com.crostage.trainchecker.presentation.appComponent
import com.crostage.trainchecker.presentation.util.Helper.Companion.showSnackBar
import com.crostage.trainchecker.presentation.viewmodel.RouteViewModel
import com.crostage.trainchecker.presentation.viewmodel.factory.RouteViewModelAssistedFactory
import com.crostage.trainchecker.utils.Constant
import javax.inject.Inject

/**
 * Фрагмент отображающий список маршуртов поезда
 *
 */
class RouteFragment : Fragment(R.layout.fragment_route) {

    private lateinit var viewModel: RouteViewModel
    private lateinit var adapter: RouteListAdapter
    private var _binding: FragmentRouteBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var assistedFactory: RouteViewModelAssistedFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.getRouteComponent().build().inject(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentRouteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createViewModel()
        initRecyclerview()
        setFromArguments(arguments)
        setObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setFromArguments(arguments: Bundle?) {
        val train = arguments?.getParcelable<Train>(Constant.TRAIN_ARG)

        train?.let {



            binding.tryAgain.setOnClickListener {
                viewModel.getRoutes(train)
                binding.tryAgain.isVisible = false
            }

            if (viewModel.routes.value == null && viewModel.error.value == null)
                viewModel.getRoutes(train)
        }
    }

    private fun initRecyclerview() {
        binding.routeRecyclerview.layoutManager =
            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        adapter = RouteListAdapter()
        binding.routeRecyclerview.addItemDecoration(
            DividerItemDecoration(
                binding.routeRecyclerview.context,
                LinearLayoutManager.VERTICAL
            )
        )
        binding.routeRecyclerview.adapter = adapter
    }


    private fun createViewModel() {
        val factory = assistedFactory.create(this)
        viewModel = ViewModelProvider(this, factory).get(RouteViewModel::class.java)
    }

    private fun setObservers() {
        viewModel.routes.observe(viewLifecycleOwner, {
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