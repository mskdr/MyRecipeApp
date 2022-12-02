package com.muhammetkdr.myrecipeapp.ui.search

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammetkdr.myrecipeapp.common.utils.Resource
import com.muhammetkdr.myrecipeapp.domain.usecase.search.SearchMealWithNameUserCase
import com.muhammetkdr.myrecipeapp.model.meal.MealModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel  @Inject constructor(
    private val searchMealWithNameUserCase: SearchMealWithNameUserCase,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val _searchedMovieList = MutableLiveData<Resource<MealModel>>()
    val searchedMealList: LiveData<Resource<MealModel>> get() = _searchedMovieList

    fun searchForMeal(searchQuery: String) = viewModelScope.launch(Dispatchers.IO) {
        if (searchQuery.isEmpty()) {
            return@launch
        }
        _searchedMovieList.value = Resource.Loading
        _searchedMovieList.value =  searchMealWithNameUserCase(searchQuery)!!
    }

    fun changeSharedPreferencesValue(mealId: Int) {
        sharedPreferences.edit().putInt("idMeal", mealId).apply()
    }
}