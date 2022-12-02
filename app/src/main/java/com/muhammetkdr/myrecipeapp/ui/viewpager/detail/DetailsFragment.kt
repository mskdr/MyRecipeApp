package com.muhammetkdr.myrecipeapp.ui.viewpager.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.muhammetkdr.myrecipeapp.base.BaseFragment
import com.muhammetkdr.myrecipeapp.common.extensions.gone
import com.muhammetkdr.myrecipeapp.common.extensions.invisible
import com.muhammetkdr.myrecipeapp.common.extensions.showSnackbar
import com.muhammetkdr.myrecipeapp.common.extensions.visible
import com.muhammetkdr.myrecipeapp.common.utils.Resource
import com.muhammetkdr.myrecipeapp.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding, DetailsViewModel>(
    FragmentDetailsBinding::inflate
) {
    override val viewModel by viewModels<DetailsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()

//        binding.tvRecipe.setOnClickListener {
//            val action =
//                DetailsFragmentDirections.actionDetailsFragmentToIngredientsFragment(viewModel.mealFromNavArgs!!)
//            findNavController().navigate(action)
//        }
    }

    private fun initObservers() {
        viewModel.meal.observe(viewLifecycleOwner) { Resource ->
            with(binding) {
                when (Resource) {
                    is Resource.Success -> {
                        Resource.data?.let {
                            meal = it.meals!![0]  // There is only 1 meal will come in here
                            detailViewGroup.visible()
                            detailsProgressBar.gone()
                        }
                    }
                    is Resource.Error -> {
                        detailViewGroup.invisible()
                        detailsProgressBar.gone()
                        requireView().showSnackbar(Resource.throwable.message.toString())
                    }
                    is Resource.Loading -> {
                        detailViewGroup.invisible()
                        detailsProgressBar.visible()
                    }
                }
            }
        }
    }
}