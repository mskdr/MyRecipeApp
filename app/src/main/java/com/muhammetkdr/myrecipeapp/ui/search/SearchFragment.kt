package com.muhammetkdr.myrecipeapp.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.muhammetkdr.myrecipeapp.R
import com.muhammetkdr.myrecipeapp.base.BaseFragment
import com.muhammetkdr.myrecipeapp.databinding.FragmentDetailViewPagerBinding
import com.muhammetkdr.myrecipeapp.databinding.FragmentSearchBinding
import com.muhammetkdr.myrecipeapp.ui.viewpager.DetailsViewPagerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment: BaseFragment<FragmentSearchBinding, SearchViewModel>(
    FragmentSearchBinding::inflate
) {
    override val viewModel by viewModels<SearchViewModel>()




}