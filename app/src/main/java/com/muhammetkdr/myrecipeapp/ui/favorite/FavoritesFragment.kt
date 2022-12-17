package com.muhammetkdr.myrecipeapp.ui.favorite


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.muhammetkdr.myrecipeapp.base.BaseFragment
import com.muhammetkdr.myrecipeapp.common.extensions.gone
import com.muhammetkdr.myrecipeapp.common.extensions.showSnackbar
import com.muhammetkdr.myrecipeapp.common.extensions.visible
import com.muhammetkdr.myrecipeapp.common.utils.Resource
import com.muhammetkdr.myrecipeapp.databinding.FragmentFavoritesBinding
import com.muhammetkdr.myrecipeapp.model.meal.Meal
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment: BaseFragment<FragmentFavoritesBinding, FavoritesViewModel>(
    FragmentFavoritesBinding::inflate
) {
    override val viewModel by viewModels<FavoritesViewModel>()
    private val favoritesAdapter: FavoritesAdapter by lazy { FavoritesAdapter(::navigateDetailPage) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRv()
        initObservers()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavoriteRecipes()
    }

    private fun initObservers(){
        viewModel.favoriteList.observe(viewLifecycleOwner){ Resource ->
            with(binding) {
                when (Resource) {
                    is Resource.Success -> {
                        Resource.data.let {
                            favoritesAdapter.submitList(it)
                            favoritesProgressBar.gone()
                        }
                    }
                    is Resource.Error -> {
                        favoritesProgressBar.gone()
                        requireView().showSnackbar(Resource.throwable.message.toString())
                    }
                    is Resource.Loading -> {
                        favoritesProgressBar.visible()
                    }
                }
            }
        }
    }

    private fun setupRv() = with(binding){
        rvFavorites.adapter = favoritesAdapter
        rvFavorites.layoutManager =
//            StaggeredGridLayoutManager(2,RecyclerView.HORIZONTAL)
            GridLayoutManager(requireContext(), 2)
    }

    private fun navigateDetailPage(item: Meal){
        val action = FavoritesFragmentDirections.actionFavoritesFragmentToDetailsViewPagerFragment()
        viewModel.saveInfoMealInSharedPref(item)
        findNavController().navigate(action)
    }
}