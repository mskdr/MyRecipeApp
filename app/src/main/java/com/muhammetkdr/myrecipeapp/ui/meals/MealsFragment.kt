package com.muhammetkdr.myrecipeapp.ui.meals

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.muhammetkdr.myrecipeapp.R
import com.muhammetkdr.myrecipeapp.base.BaseFragment
import com.muhammetkdr.myrecipeapp.common.extensions.gone
import com.muhammetkdr.myrecipeapp.common.extensions.showSnackbar
import com.muhammetkdr.myrecipeapp.common.extensions.visible
import com.muhammetkdr.myrecipeapp.common.utils.Resource
import com.muhammetkdr.myrecipeapp.databinding.FragmentDetailBinding
import com.muhammetkdr.myrecipeapp.databinding.FragmentMealsBinding
import com.muhammetkdr.myrecipeapp.model.category.Category
import com.muhammetkdr.myrecipeapp.model.meal.Meal
import com.muhammetkdr.myrecipeapp.ui.detail.DetailViewModel
import com.muhammetkdr.myrecipeapp.ui.home.HomeCategoryAdapter
import com.muhammetkdr.myrecipeapp.ui.home.HomeFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealsFragment : BaseFragment<FragmentMealsBinding, MealsViewModel>(
    FragmentMealsBinding::inflate
) {
    override val viewModel by viewModels<MealsViewModel>()
    private val adapter: MealsAdapter by lazy { MealsAdapter(::navigateDetailPage) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRV()
        initObservers()
    }

    fun initObservers(){
        viewModel.mealList.observe(viewLifecycleOwner) { Resource ->
            with(binding) {
                when (Resource) {
                    is Resource.Success -> {
                        Resource.data.let { listOfMeals ->
                            val meals = listOfMeals.meals
                            adapter.submitList(meals)
                            mealsProgressbar.gone()
                        }
                    }
                    is Resource.Error -> {
                        mealsProgressbar.gone()
                        requireView().showSnackbar(Resource.throwable.message.toString())
                    }
                    is Resource.Loading -> {
                        mealsProgressbar.visible()
                    }
                }
            }
        }
    }

    private fun setupRV() = with(binding) {
        rvMeals.adapter = adapter
        rvMeals.layoutManager =
            GridLayoutManager(requireContext(), 2)
    }

    private fun navigateDetailPage(item: Meal) {
//        val action = HomeFragmentDirections.actionHomeFragmentToMealsFragment()
//        findNavController().navigate(action)
    }






}