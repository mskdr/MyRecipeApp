package com.muhammetkdr.myrecipeapp.ui.viewpager.detail

import android.content.SharedPreferences
import androidx.lifecycle.*
import com.muhammetkdr.myrecipeapp.common.utils.Resource
import com.muhammetkdr.myrecipeapp.domain.usecase.DeleteRecipeUseCase
import com.muhammetkdr.myrecipeapp.domain.usecase.GetFavMealIfExistUseCase
import com.muhammetkdr.myrecipeapp.domain.usecase.InsertRecipeUseCase
import com.muhammetkdr.myrecipeapp.domain.usecase.search.FindMealByIdUseCase
import com.muhammetkdr.myrecipeapp.model.meal.Meal
import com.muhammetkdr.myrecipeapp.model.meal.MealModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    sharedPref: SharedPreferences,
    private val insertRecipeUseCase: InsertRecipeUseCase,
    private val deleteRecipeUseCase: DeleteRecipeUseCase,
    private val findMealByIdUseCase: FindMealByIdUseCase,
    private val getFavMealIfExistUseCase: GetFavMealIfExistUseCase
) : ViewModel() {

    private val idMeal = sharedPref.getInt("idMeal", 0)

    private val _meal: MutableLiveData<Resource<MealModel>> = MutableLiveData(Resource.Loading)
    val meal: LiveData<Resource<MealModel>> get() = _meal

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = _isFavorite

    init {
        _isFavorite.value = false
        getMealById(idMeal)
        getFavMealIfExist(idMeal)
    }

    fun setFavoriteState(meal: Meal) = viewModelScope.launch{
            if (_isFavorite.value == true) {
                deleteRecipeUseCase(meal)
                _isFavorite.value = false
            } else {
                insertRecipeUseCase(meal)
                _isFavorite.value = true
            }
    }

    fun getMealById(id: Int) = viewModelScope.launch {
        if (idMeal != 0) {
            _meal.value = Resource.Loading
            _meal.value = findMealByIdUseCase(id)!!
        }
    }

    fun getFavMealIfExist(idMeal : Int) = viewModelScope.launch{
        _isFavorite.value = getFavMealIfExistUseCase(idMeal)!!
    }
}