package com.muhammetkdr.myrecipeapp.ui.favorite

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammetkdr.myrecipeapp.common.utils.Resource
import com.muhammetkdr.myrecipeapp.domain.usecase.ProvideRecipeUseCase
import com.muhammetkdr.myrecipeapp.model.meal.Meal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val provideRecipeUseCase: ProvideRecipeUseCase
) : ViewModel() {

    private val _favoriteList = MutableLiveData<Resource<List<Meal>>>()
    val favoriteList: LiveData<Resource<List<Meal>>> = _favoriteList

    init {
        getFavoriteRecipes()
    }

    private fun getFavoriteRecipes() = viewModelScope.launch{
            _favoriteList.value = provideRecipeUseCase()!!
    }

    fun saveInfoMealInSharedPref(meal: Meal) {
        sharedPreferences.edit().putInt("idMeal", meal.idMeal!!.toInt()).apply()
    }
}