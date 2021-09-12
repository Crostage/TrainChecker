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
import com.crostage.trainchecker.presentation.util.Helper
import com.crostage.trainchecker.presentation.util.Helper.Companion.showSnackBar
import com.crostage.trainchecker.presentation.viewmodel.RouteViewModel
import com.crostage.trainchecker.presentation.viewmodel.factory.RouteViewModelFactory
import com.crostage.trainchecker.utils.Constant
import javax.inject.Inject


class RouteFragment : Fragment(R.layout.fragment_route) {

    private lateinit var viewModel: RouteViewModel
    private lateinit var adapter: RouteListAdapter
    private lateinit var binding: FragmentRouteBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.getRouteComponent().build().inject(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentRouteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerview()
        setFromArguments(arguments)

    }


    private fun setFromArguments(arguments: Bundle?) {
        val train = arguments?.getParcelable<Train>(Constant.TRAIN_ARG)

        train?.let {

            setObservers()

            binding.tryAgain.setOnClickListener {
                viewModel.getRoutes(train)
                binding.tryAgain.isVisible = false
            }

            if (viewModel.routes.value == null)
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

    @Inject
    fun createViewModel(factory: RouteViewModelFactory) {
        viewModel = ViewModelProvider(this, factory).get(RouteViewModel::class.java)
    }

    private fun setObservers() {
        viewModel.routes.observe(viewLifecycleOwner, {
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