package com.muhammetkdr.myrecipeapp.ui.viewpager.detail

import android.content.SharedPreferences
import androidx.lifecycle.*
import com.muhammetkdr.myrecipeapp.common.utils.Resource
import com.muhammetkdr.myrecipeapp.domain.usecase.search.FindMealByIdUseCase
import com.muhammetkdr.myrecipeapp.model.meal.Meal
import com.muhammetkdr.myrecipeapp.model.meal.MealModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    sharedPref: SharedPreferences,
    private val findMealByIdUseCase: FindMealByIdUseCase
) : ViewModel() {

    private val idMeal = sharedPref.getInt("idMeal", 0)

    private val _meal: MutableLiveData<Resource<MealModel>> = MutableLiveData(Resource.Loading)
    val meal: LiveData<Resource<MealModel>> get() = _meal

    init {
        getMealById()
    }

    fun getMealById() = viewModelScope.launch {
        if (idMeal != 0) {
            _meal.value = Resource.Loading
            _meal.value = findMealByIdUseCase(idMeal)!!
        }
    }
}