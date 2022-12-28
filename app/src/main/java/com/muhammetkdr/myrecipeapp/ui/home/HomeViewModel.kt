package com.muhammetkdr.myrecipeapp.ui.home

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammetkdr.myrecipeapp.common.utils.Resource
import com.muhammetkdr.myrecipeapp.domain.usecase.list.GetCategoriesUseCase
import com.muhammetkdr.myrecipeapp.domain.usecase.list.RandomMealUseCase
import com.muhammetkdr.myrecipeapp.model.category.CategoryModel
import com.muhammetkdr.myrecipeapp.model.meal.Meal
import com.muhammetkdr.myrecipeapp.model.meal.MealModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val getCategoriesUseCase : GetCategoriesUseCase,
    private val getRandomMeal : RandomMealUseCase
) : ViewModel() {
    
    private val _categoryList : MutableLiveData<Resource<CategoryModel>> = MutableLiveData(Resource.Loading)
    val categoryList: LiveData<Resource<CategoryModel>> get() = _categoryList

    private val _randomMeal= MutableLiveData<Resource<MealModel>>()
    val randomMeal: LiveData<Resource<MealModel>> get() = _randomMeal

    init {
        getCategories()
    }

    private fun getCategories() = viewModelScope.launch {
            _categoryList.value = Resource.Loading
            _categoryList.value = getCategoriesUseCase()!!
        }

    fun provideRandomMeal() = viewModelScope.launch {
        _randomMeal.value = Resource.Loading
        _randomMeal.value = getRandomMeal()!!
    }

    fun saveInfoMealInSharedPref(meal: Meal){
        sharedPreferences.edit().putInt("idMeal",meal.idMeal!!.toInt()).apply()
    }
}
