package com.muhammetkdr.myrecipeapp.ui.search

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.muhammetkdr.myrecipeapp.R
import com.muhammetkdr.myrecipeapp.base.BaseFragment
import com.muhammetkdr.myrecipeapp.common.extensions.gone
import com.muhammetkdr.myrecipeapp.common.extensions.showSnackbar
import com.muhammetkdr.myrecipeapp.common.extensions.visible
import com.muhammetkdr.myrecipeapp.common.utils.Resource
import com.muhammetkdr.myrecipeapp.databinding.FragmentSearchBinding
import com.muhammetkdr.myrecipeapp.model.meal.Meal
import com.muhammetkdr.myrecipeapp.ui.meals.MealsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>(
    FragmentSearchBinding::inflate
) {
    override val viewModel by viewModels<SearchViewModel>()
    private val mealsAdapter: MealsAdapter by lazy { MealsAdapter(::navigateDetailPage) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRV()
        searchMeal()
        initObservers()

    }

    private fun searchMeal() {
        var job: Job? = null
        binding.searchMealTextField.editText?.addTextChangedListener {
            job?.cancel()
            job = lifecycleScope.launch {
                delay(1000)
                it?.let {
                    if (it.toString().isNotEmpty()) {
                        viewModel.searchMealWithQuery(it.toString())
                    }
                }
            }
        }
    }

    private fun initObservers() {
        viewModel.searchedMealList.observe(viewLifecycleOwner) { Resource ->
            with(binding) {
                when (Resource) {
                    is Resource.Success -> {
                        Resource.data.let { listOfMeals ->
                            val meals = listOfMeals.meals
                            mealsAdapter.submitList(meals)
                            searchProgressBar.gone()
                        }
                    }
                    is Resource.Error -> {
                        searchProgressBar.gone()
                        requireView().showSnackbar(
                            Resource.throwable.localizedMessage ?: resources.getString(R.string.someting_bad_happened)
                        )
                    }
                    is Resource.Loading -> {
                        searchProgressBar.visible()
                    }
                }
            }
        }
    }

    private fun setupRV() = with(binding) {
        rvSearch.adapter = mealsAdapter
        rvSearch.layoutManager =
            GridLayoutManager(requireContext(), 2)
    }

    private fun navigateDetailPage(item: Meal) {
        val action = SearchFragmentDirections.actionSearchFragmentToDetailsViewPagerFragment()
        viewModel.saveInfoMealInSharedPref(item)
        findNavController().navigate(action)
    }
}