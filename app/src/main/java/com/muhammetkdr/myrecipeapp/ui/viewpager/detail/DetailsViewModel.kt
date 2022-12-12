package com.muhammetkdr.myrecipeapp.ui.viewpager.detail

import android.content.SharedPreferences
import androidx.lifecycle.*
import com.muhammetkdr.myrecipeapp.common.utils.Resource
import com.muhammetkdr.myrecipeapp.domain.usecase.DeleteFromFavoritesUserCase
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
    private val deleteFromFavoritesUserCase: DeleteFromFavoritesUserCase,
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

    private fun deletefromFavorite(id:Int) = viewModelScope.launch {
        deleteFromFavoritesUserCase(id)
    }

    private fun insertFav(meal: Meal) = viewModelScope.launch {
        insertRecipeUseCase(meal)
    }

    fun setFavoriteState(meal: Meal){
            if (_isFavorite.value == true) {
                deletefromFavorite(meal.idMeal!!.toInt())
                _isFavorite.value = false
            } else {
                insertFav(meal)
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