package com.muhammetkdr.myrecipeapp.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.muhammetkdr.myrecipeapp.base.BaseFragment
import com.muhammetkdr.myrecipeapp.common.utils.Resource
import com.muhammetkdr.myrecipeapp.databinding.FragmentHomeBinding
import com.muhammetkdr.myrecipeapp.model.category.Category
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    FragmentHomeBinding::inflate
) {
    override val viewModel by viewModels<HomeViewModel>()
    private val adapter: HomeCategoryAdapter by lazy { HomeCategoryAdapter(::navigateDetailPage) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRV()
        initObservers()


    }

    fun initObservers(){
        viewModel.categoryList.observe(viewLifecycleOwner){ Resource->
            when (Resource) {
                is Resource.Success -> {
                    Resource.data.let { listOfCategory ->
                        val category = listOfCategory.categories
                        adapter.submitList(category)
                    }
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(),"Error", Toast.LENGTH_LONG)
                        .show()

                }
                is Resource.Loading -> {

                }
            }
        }

    }

    private fun setupRV() {
        binding.rvCategoryHome.adapter = adapter
    }

    private fun navigateDetailPage(item: Category) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(item)
        findNavController().navigate(action)
    }

}
