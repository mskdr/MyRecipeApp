package com.muhammetkdr.myrecipeapp.ui.viewpager.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.muhammetkdr.myrecipeapp.R
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
        initDataBinding()
    }

    private fun initDataBinding() = with(binding) {
        detailsViewModel = viewModel
        lifecycleOwner = viewLifecycleOwner
    }

    private fun initObservers() {
        viewModel.meal.observe(viewLifecycleOwner) { Resource ->
            with(binding) {
                when (Resource) {
                    is Resource.Success -> {
                        Resource.data.let { mealModel ->
                            mealModel.meals?.let {
                                meal = mealModel.meals[0]  // There is only 1 meal will come in here as a response
                            }
                            detailViewGroup.visible()
                            detailsProgressBar.gone()
                        }
                    }
                    is Resource.Error -> {
                        detailViewGroup.invisible()
                        detailsProgressBar.gone()
                        requireView().showSnackbar(
                            Resource.throwable.localizedMessage ?: resources.getString(R.string.someting_bad_happened))
                    }
                    is Resource.Loading -> {
                        detailViewGroup.invisible()
                        detailsProgressBar.visible()
                    }
                }
            }
        }

        viewModel.isFavorite.observe(viewLifecycleOwner) { favorite ->
            initImgFavOnClick(favorite)
        }
    }

    private fun initImgFavOnClick(isFavorite: Boolean) = with(binding) {
        imgFavorite.setOnClickListener {
            if (isFavorite) {
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle(resources.getString(R.string.dialog_title))
                    .setMessage(resources.getString(R.string.dialog_body))
                    .setPositiveButton(resources.getString(R.string.dialog_accept)) { _, _ ->
                        Toast.makeText(
                            requireContext(), R.string.toast_accepted, Toast.LENGTH_SHORT
                        ).show()

                        meal?.let { meal ->
                            viewModel.setFavoriteState(meal)
                        }
                    }
                    .setNegativeButton(resources.getString(R.string.dialog_decline)) { _, _ ->
                        Toast.makeText(requireContext(), R.string.toast_declined, Toast.LENGTH_SHORT
                        ).show()
                    }.show()
            } else {
                Toast.makeText(requireContext(), R.string.toast_added, Toast.LENGTH_SHORT).show()
                meal?.let { meal ->
                    viewModel.setFavoriteState(meal)
                }
            }
        }
    }
}