package com.muhammetkdr.myrecipeapp.ui.home

import androidx.fragment.app.viewModels
import com.muhammetkdr.myrecipeapp.base.BaseFragment
import com.muhammetkdr.myrecipeapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    FragmentHomeBinding::inflate
) {
    override val viewModel by viewModels<HomeViewModel>()


    override fun onCreateFinished() {

    }

    override fun observeEvents() {
    
    }

}
