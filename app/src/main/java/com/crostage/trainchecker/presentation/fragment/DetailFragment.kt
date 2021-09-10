package com.crostage.trainchecker.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.crostage.trainchecker.R
import com.crostage.trainchecker.databinding.FragmentDetailBinding
import com.crostage.trainchecker.domain.model.Train
import com.crostage.trainchecker.presentation.adapter.PagerAdapter
import com.crostage.trainchecker.utils.Constant
import com.google.android.material.tabs.TabLayoutMediator

class DetailFragment : Fragment(R.layout.fragment_detail) {


    private lateinit var binding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFromArguments()
    }

    private fun setFromArguments() {

        val train = args.train

        binding.toolbarDetail.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

        binding.trainNumber.text = train.trainNumber
        "${train.nameStationFrom} -> ${train.nameStationTo}".also { binding.fromTo.text = it }
        binding.date.text = train.dateStart
        initViewPager(train)
    }

    private fun initViewPager(train: Train) {

        val bundle = Bundle()
        bundle.putParcelable(Constant.TRAIN_ARG, train)

        val routeFragment = RouteFragment()
        routeFragment.arguments = bundle

        val seatFragment = SeatFragment()
        seatFragment.arguments = bundle

        val fragmentList = listOf(routeFragment, seatFragment)

        val pageAdapter =
            PagerAdapter(fragmentList, childFragmentManager, lifecycle)
        binding.fragmentPager.adapter = pageAdapter

        TabLayoutMediator(binding.detailTab, binding.fragmentPager) { tab, pos ->
            when (pos) {
                0 -> tab.text = resources.getString(R.string.routes)
                1 -> tab.text = resources.getString(R.string.seats)
            }
        }.attach()
    }

}
