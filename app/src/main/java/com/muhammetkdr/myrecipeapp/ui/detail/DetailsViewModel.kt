package com.muhammetkdr.myrecipeapp.ui.detail

import androidx.lifecycle.*
import com.muhammetkdr.myrecipeapp.common.utils.Resource
import com.muhammetkdr.myrecipeapp.domain.usecase.search.FindMealByIdUseCase
import com.muhammetkdr.myrecipeapp.model.category.Category
import com.muhammetkdr.myrecipeapp.model.meal.Meal
import com.muhammetkdr.myrecipeapp.model.meal.MealModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val findMealByIdUseCase: FindMealByIdUseCase
) : ViewModel() {

    private val mealFromNavArgs: Meal? = savedStateHandle.get<Meal>("mealList")

    private val _meal: MutableLiveData<Resource<MealModel>> = MutableLiveData(Resource.Loading)
    val meal: LiveData<Resource<MealModel>> get() = _meal

    init {
        getMealById()
    }

    fun getMealById() = viewModelScope.launch{
        mealFromNavArgs?.let {
            _meal.value = Resource.Loading
            _meal.value = findMealByIdUseCase(it.idMeal!!.toInt())
        }
    }

}