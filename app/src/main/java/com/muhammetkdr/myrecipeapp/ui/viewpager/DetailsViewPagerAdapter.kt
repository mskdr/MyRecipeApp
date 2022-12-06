package com.muhammetkdr.myrecipeapp.ui.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.muhammetkdr.myrecipeapp.common.utils.Const.Companion.DETAIL_PAGE_INDEX
import com.muhammetkdr.myrecipeapp.common.utils.Const.Companion.INGREDIENTS_PAGE_INDEX
import com.muhammetkdr.myrecipeapp.ui.viewpager.detail.DetailsFragment
import com.muhammetkdr.myrecipeapp.ui.viewpager.ingredients.IngredientsFragment
import javax.inject.Inject


class DetailsViewPagerAdapter @Inject constructor(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val tabFragmentsCreator: Map<Int, () -> Fragment> = mapOf(
        DETAIL_PAGE_INDEX to { DetailsFragment() },
        INGREDIENTS_PAGE_INDEX to { IngredientsFragment() }
    )

    override fun getItemCount(): Int = tabFragmentsCreator.size

    override fun createFragment(position: Int): Fragment =
        tabFragmentsCreator[position]?.invoke() ?: throw IndexOutOfBoundsException()
}