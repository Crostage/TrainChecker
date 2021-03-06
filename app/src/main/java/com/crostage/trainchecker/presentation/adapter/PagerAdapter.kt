package com.crostage.trainchecker.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.crostage.trainchecker.utils.Constant.Companion.TAB_ITEM_COUNT


/**
 * Адаптер для переключения фрагментов
 */
class PagerAdapter(
    private val fragmentList: List<Fragment>,
    fa: FragmentManager,
    lifecycle: Lifecycle,
) : FragmentStateAdapter(fa, lifecycle) {
    override fun getItemCount(): Int = TAB_ITEM_COUNT

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}