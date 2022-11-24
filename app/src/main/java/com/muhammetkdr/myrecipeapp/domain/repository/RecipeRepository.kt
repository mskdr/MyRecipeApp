package com.muhammetkdr.myrecipeapp.domain.repository

import androidx.lifecycle.LiveData
import com.muhammetkdr.myrecipeapp.common.utils.Resource
import com.muhammetkdr.myrecipeapp.model.meal.Meal
import com.muhammetkdr.myrecipeapp.model.meal.MealModel

interface RecipeRepository {

    suspend fun searchMealWithName(searchWithName: String): Resource<MealModel>

    suspend fun searchMealWithFirstLetter(searchWithFirstLetter: String): Resource<MealModel>

    suspend fun lookUpWithId(searchMealId: Int): Resource<MealModel>

    suspend fun randomMeal(): Resource<MealModel>

//    suspend fun categories() : Categories

    suspend fun listByType(): Resource<MealModel>

    suspend fun listByCountry(): Resource<MealModel>

    suspend fun allMealList(): Resource<MealModel>

    suspend fun filterByItemName(itemName: String): Resource<MealModel>

    suspend fun filterByCategory(categoryName: String): Resource<MealModel>

    suspend fun filterByNation(nationName: String): Resource<MealModel>

    suspend fun insertRecipe(meal: Meal)

    suspend fun deleteRecipe(meal: Meal)

    suspend fun provideRecipes(): LiveData<List<Meal>>

    suspend fun isRowExist(idMeal : Int?) : Boolean
}