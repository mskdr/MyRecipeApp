package com.muhammetkdr.myrecipeapp.ui.meals

import android.content.SharedPreferences
import androidx.lifecycle.*
import com.muhammetkdr.myrecipeapp.common.utils.Resource
import com.muhammetkdr.myrecipeapp.domain.usecase.list.filter.FilterByCategoryUseCase
import com.muhammetkdr.myrecipeapp.model.category.Category
import com.muhammetkdr.myrecipeapp.model.meal.Meal
import com.muhammetkdr.myrecipeapp.model.meal.MealModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val sharedPreferences: SharedPreferences,
    private val listByCategoryUseCase: FilterByCategoryUseCase
) : ViewModel() {

    private val mealInCategory: Category? = savedStateHandle.get<Category>("categoryList")

    private val _mealList: MutableLiveData<Resource<MealModel>> = MutableLiveData(Resource.Loading)
    val mealList: LiveData<Resource<MealModel>> get() = _mealList

    init {
        getMealsByCategory()
    }

    fun getMealsByCategory() = viewModelScope.launch {
        mealInCategory?.let {
            _mealList.value = Resource.Loading
            _mealList.value = listByCategoryUseCase(it.strCategory!!)!!
        }
    }

    fun saveInfoMealInSharedPref(meal :Meal ){
        sharedPreferences.edit().putInt("idMeal",meal.idMeal!!.toInt()).apply()
//        sharedPreferences.edit().putBoolean("mealFavoriteState",meal.isFavorite).apply()
    }



}