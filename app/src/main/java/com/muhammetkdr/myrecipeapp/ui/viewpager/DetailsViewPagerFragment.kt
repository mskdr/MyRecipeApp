package com.muhammetkdr.myrecipeapp.ui.viewpager

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.muhammetkdr.myrecipeapp.R
import com.muhammetkdr.myrecipeapp.base.BaseFragment
import com.muhammetkdr.myrecipeapp.common.utils.Const.Companion.DETAIL_PAGE_INDEX
import com.muhammetkdr.myrecipeapp.common.utils.Const.Companion.INGREDIENTS_PAGE_INDEX
import com.muhammetkdr.myrecipeapp.databinding.FragmentDetailViewPagerBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailsViewPagerFragment : BaseFragment<FragmentDetailViewPagerBinding, DetailsViewPagerViewModel>(
    FragmentDetailViewPagerBinding::inflate
) {
    override val viewModel by viewModels<DetailsViewPagerViewModel>()

    @Inject
    lateinit var viewPagerAdapter : DetailsViewPagerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()

    }

    private fun setupUI() = with(binding){
        viewPagerDetails.adapter = viewPagerAdapter
        TabLayoutMediator(tabLayoutDetails, viewPagerDetails) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()
    }


    private fun getTabTitle(position: Int): String? {
        return when (position) {
            DETAIL_PAGE_INDEX -> getString(R.string.fragment_details)
            INGREDIENTS_PAGE_INDEX -> getString(R.string.fragment_ingredients)
            else -> null
        }
    }



}
