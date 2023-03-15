package com.muhammetkdr.myrecipeapp.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.muhammetkdr.myrecipeapp.R
import com.muhammetkdr.myrecipeapp.base.BaseFragment
import com.muhammetkdr.myrecipeapp.common.extensions.gone
import com.muhammetkdr.myrecipeapp.common.extensions.showSnackbar
import com.muhammetkdr.myrecipeapp.common.extensions.visible
import com.muhammetkdr.myrecipeapp.common.utils.Resource
import com.muhammetkdr.myrecipeapp.databinding.FragmentHomeBinding
import com.muhammetkdr.myrecipeapp.model.category.Category
import com.muhammetkdr.myrecipeapp.model.meal.Meal
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    FragmentHomeBinding::inflate
) {
    override val viewModel by viewModels<HomeViewModel>()
    private val adapter: HomeCategoryAdapter by lazy { HomeCategoryAdapter(::navigateMealsPage) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRV()
        initObservers()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getMealRandomly()
    }

    private fun initObservers() {
        viewModel.categoryList.observe(viewLifecycleOwner) { Resource ->
            with(binding) {
                when (Resource) {
                    is Resource.Success -> {
                        Resource.data.let { listOfCategory ->
                            val category = listOfCategory.categories
                            adapter.submitList(category)
                            homeViewGroup.visible()
                            homeProgressbar.gone()
                        }
                    }
                    is Resource.Error -> {
                        homeViewGroup.gone()
                        homeProgressbar.gone()
                        requireView().showSnackbar(
                            Resource.throwable.localizedMessage ?: resources.getString(R.string.someting_bad_happened))
                    }
                    is Resource.Loading -> {
                        homeViewGroup.gone()
                        homeProgressbar.visible()
                    }
                }
            }
        }

        viewModel.randomMeal.observe(viewLifecycleOwner) { Resource ->
            with(binding) {
                when (Resource) {
                    is Resource.Success -> {
                        Resource.data.let { mealModel ->

                            mealModel.meals?.let { mealList ->
                                val randomMeal =
                                    mealList[0] // There is only 1 meal will come in here as a response

                                fabHome.setOnClickListener {
                                    randomMeal?.let {
                                        buildAlertDialog(it)
                                    }
                                }

                            }
                        }
                    }
                    is Resource.Error -> {}
                    is Resource.Loading -> {}
                }
            }
        }
    }

    private fun buildAlertDialog(randomMeal: Meal) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(resources.getString(R.string.dialog_title))
            .setMessage(resources.getString(R.string.home_dialog_body))
            .setPositiveButton(resources.getString(R.string.dialog_accept)) { dialog, which ->
                viewModel.saveInfoMealInSharedPref(randomMeal)
                val action = HomeFragmentDirections.actionHomeFragmentToDetailsViewPagerFragment()
                findNavController().navigate(action)
            }.setNegativeButton(resources.getString(R.string.dialog_decline)) { dialog, which ->
                Toast.makeText(
                    requireContext(), R.string.toast_declined, Toast.LENGTH_SHORT
                ).show()
            }.show()
    }

    private fun setupRV() = with(binding) {
        rvCategoryHome.adapter = adapter
        rvCategoryHome.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    private fun navigateMealsPage(item: Category) {
        val action = HomeFragmentDirections.actionHomeFragmentToMealsFragment(item)
        findNavController().navigate(action)
    }
}
