package com.muhammetkdr.myrecipeapp.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.muhammetkdr.myrecipeapp.base.BaseFragment
import com.muhammetkdr.myrecipeapp.common.extensions.gone
import com.muhammetkdr.myrecipeapp.common.extensions.showSnackbar
import com.muhammetkdr.myrecipeapp.common.extensions.visible
import com.muhammetkdr.myrecipeapp.common.utils.Resource
import com.muhammetkdr.myrecipeapp.databinding.FragmentHomeBinding
import com.muhammetkdr.myrecipeapp.model.category.Category
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
                        requireView().showSnackbar(Resource.throwable.message.toString())
                    }
                    is Resource.Loading -> {
                        homeViewGroup.gone()
                        homeProgressbar.visible()
                    }
                }
            }
        }
    }

    private fun setupRV() = with(binding) {
        rvCategoryHome.adapter = adapter
        rvCategoryHome.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun navigateMealsPage(item: Category) {
        val action = HomeFragmentDirections.actionHomeFragmentToMealsFragment(item)
        findNavController().navigate(action)
    }

}
