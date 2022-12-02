package com.muhammetkdr.myrecipeapp.ui.viewpager.ingredients



import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.muhammetkdr.myrecipeapp.base.BaseFragment
import com.muhammetkdr.myrecipeapp.common.extensions.gone
import com.muhammetkdr.myrecipeapp.common.extensions.showSnackbar
import com.muhammetkdr.myrecipeapp.common.extensions.visible
import com.muhammetkdr.myrecipeapp.common.utils.Resource
import com.muhammetkdr.myrecipeapp.databinding.FragmentIngredientsBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class IngredientsFragment : BaseFragment<FragmentIngredientsBinding, IngredientsViewModel>(
    FragmentIngredientsBinding::inflate
) {
    override val viewModel by viewModels<IngredientsViewModel>()

    @Inject
    lateinit var ingredientsListAdapter: IngredientsListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRV()
        initObservers()
    }

    private fun initObservers() {
        viewModel.meal.observe(viewLifecycleOwner) { Resource ->
            with(binding) {
                when (Resource) {
                    is Resource.Success -> {
                        Resource.data?.let {
                            val ingredients = listOf(
                                it.meals!![0]?.strIngredient1,
                                it.meals[0]?.strIngredient2,
                                it.meals[0]?.strIngredient3,
                                it.meals[0]?.strIngredient4,
                                it.meals[0]?.strIngredient5,
                                it.meals[0]?.strIngredient6,
                                it.meals[0]?.strIngredient7,
                                it.meals[0]?.strIngredient8,
                                it.meals[0]?.strIngredient9,
                                it.meals[0]?.strIngredient10,
                                it.meals[0]?.strIngredient11,
                                it.meals[0]?.strIngredient12,
                                it.meals[0]?.strIngredient13,
                                it.meals[0]?.strIngredient14,
                                it.meals[0]?.strIngredient15,
                                it.meals[0]?.strIngredient16,
                                it.meals[0]?.strIngredient17,
                                it.meals[0]?.strIngredient18,
                                it.meals[0]?.strIngredient19,
                                it.meals[0]?.strIngredient20)

                            val measures = listOf(
                                it.meals[0]?.strMeasure1,
                                it.meals[0]?.strMeasure2,
                                it.meals[0]?.strMeasure3,
                                it.meals[0]?.strMeasure4,
                                it.meals[0]?.strMeasure5,
                                it.meals[0]?.strMeasure6,
                                it.meals[0]?.strMeasure7,
                                it.meals[0]?.strMeasure8,
                                it.meals[0]?.strMeasure9,
                                it.meals[0]?.strMeasure10,
                                it.meals[0]?.strMeasure11,
                                it.meals[0]?.strMeasure12,
                                it.meals[0]?.strMeasure13,
                                it.meals[0]?.strMeasure14,
                                it.meals[0]?.strMeasure15,
                                it.meals[0]?.strMeasure16,
                                it.meals[0]?.strMeasure17,
                                it.meals[0]?.strMeasure18,
                                it.meals[0]?.strMeasure19,
                                it.meals[0]?.strMeasure20)

                            ingredients.filterNotNull()
                            measures.filterNotNull()

                            ingredientsListAdapter.DifferForIngredients.submitList(ingredients)
                            ingredientsListAdapter.DifferForMeasures.submitList(measures)
                            ingredientsProgressbar.gone()
                        }
                    }
                    is Resource.Error -> {
                        ingredientsProgressbar.gone()
                        requireView().showSnackbar(Resource.throwable.message.toString())
                    }
                    is Resource.Loading -> {
                        ingredientsProgressbar.visible()
                    }
                }
            }
        }
    }

    private fun setupRV() {
        with(binding) {
            rvIngredients.adapter = ingredientsListAdapter
            rvIngredients.layoutManager = LinearLayoutManager(requireContext())
        }
    }
}