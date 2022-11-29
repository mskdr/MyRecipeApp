package com.muhammetkdr.myrecipeapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.muhammetkdr.myrecipeapp.R
import com.muhammetkdr.myrecipeapp.base.BaseFragment
import com.muhammetkdr.myrecipeapp.common.extensions.gone
import com.muhammetkdr.myrecipeapp.common.extensions.showSnackbar
import com.muhammetkdr.myrecipeapp.common.extensions.visible
import com.muhammetkdr.myrecipeapp.common.utils.Resource
import com.muhammetkdr.myrecipeapp.databinding.FragmentDetailsBinding
import com.muhammetkdr.myrecipeapp.databinding.FragmentHomeBinding
import com.muhammetkdr.myrecipeapp.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment  : BaseFragment<FragmentDetailsBinding, DetailsViewModel>(
    FragmentDetailsBinding::inflate
) {
    override val viewModel by viewModels<DetailsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    private fun initObservers(){
        viewModel.meal.observe(viewLifecycleOwner){ Resource ->
            with(binding) {
                when (Resource) {
                    is Resource.Success -> {
                        Resource.data.let {
                            binding.meal = it.meals!![0]  // There is only 1 meal in here
                            detailsFragmentViews.visible()
                            detailsProgressBar.gone()
                        }
                    }
                    is Resource.Error -> {
                        detailsFragmentViews.gone()
                        detailsProgressBar.gone()
                        requireView().showSnackbar(Resource.throwable.message.toString())
                    }
                    is Resource.Loading -> {
                        detailsFragmentViews.gone()
                        detailsProgressBar.visible()
                    }
                }
            }
        }
    }
}