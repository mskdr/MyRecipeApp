package com.muhammetkdr.myrecipeapp.domain.source.local

import androidx.lifecycle.LiveData
import com.muhammetkdr.myrecipeapp.model.meal.Meal

interface LocalDataSource {

    suspend fun insertRecipe(meal: Meal)

    suspend fun deleteRecipe(meal: Meal)

    suspend fun provideRecipes(): LiveData<List<Meal>>

    suspend fun getFavMealIfExist(idMeal : Int?) : Boolean
}