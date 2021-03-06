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


/**
 * Фрагмент отображающий детальную информацию о поезде.
 * Включает в себя два фрагмента [RouteFragment] и [CarFragment]
 *
 *
 */
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFromArguments()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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

        val seatFragment = CarFragment()
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
