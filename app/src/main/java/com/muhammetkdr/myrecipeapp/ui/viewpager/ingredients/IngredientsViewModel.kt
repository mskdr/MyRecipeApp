package com.muhammetkdr.myrecipeapp.ui.viewpager.ingredients

import android.content.SharedPreferences
import androidx.lifecycle.*
import com.muhammetkdr.myrecipeapp.common.utils.Resource
import com.muhammetkdr.myrecipeapp.domain.usecase.search.FindMealByIdUseCase
import com.muhammetkdr.myrecipeapp.model.meal.MealModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IngredientsViewModel @Inject constructor(
    sharedPref: SharedPreferences,
    private val findMealByIdUseCase: FindMealByIdUseCase
) : ViewModel() {

    private val idMeal = sharedPref.getInt("idMeal", 0)

    private val _meal: MutableLiveData<Resource<MealModel>> = MutableLiveData(Resource.Loading)
    val meal: LiveData<Resource<MealModel>> get() = _meal

    private val _ingredientList = mutableListOf<String>()
    val ingredientList: List<String> get() = _ingredientList

    private val _measureList = mutableListOf<String>()
    val measureList: List<String> get() = _measureList

    init {
        getMealById()
    }

    fun getMealById() = viewModelScope.launch {
        if (idMeal != 0) {
            _meal.value = Resource.Loading
            _meal.value = findMealByIdUseCase(idMeal)!!
        }
    }

    fun removeNullOrEmpty(itemIngredients: List<String?>?, itemMeasures: List<String?>?) {
        itemIngredients?.forEach { ingredient ->
            if (ingredient.isNullOrEmpty()) {
                return@forEach
            }
            _ingredientList.add(ingredient)
        }

        itemMeasures?.forEach { measure ->
            if (measure.isNullOrEmpty()) {
                return@forEach
            }
            _measureList.add(measure)
        }
    }

}