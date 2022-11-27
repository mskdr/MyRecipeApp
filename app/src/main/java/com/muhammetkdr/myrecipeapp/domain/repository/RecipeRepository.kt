package com.muhammetkdr.myrecipeapp.domain.repository

import androidx.lifecycle.LiveData
import com.muhammetkdr.myrecipeapp.model.category.CategoryModel
import com.muhammetkdr.myrecipeapp.model.meal.Meal
import com.muhammetkdr.myrecipeapp.model.meal.MealModel

interface RecipeRepository {

    suspend fun searchMealWithName(searchWithName: String): MealModel

    suspend fun searchMealWithFirstLetter(searchWithFirstLetter: String): MealModel

    suspend fun findMealById(searchMealId: Int): MealModel

    suspend fun randomMeal(): MealModel

    suspend fun getCategories() : CategoryModel

    suspend fun listByType(): MealModel

    suspend fun listByCountry(): MealModel

    suspend fun allMealList(): MealModel

    suspend fun filterByItemName(itemName: String): MealModel

    suspend fun filterByCategory(categoryName: String): MealModel

    suspend fun filterByNation(nationName: String): MealModel

    suspend fun insertRecipe(meal: Meal)

    suspend fun deleteRecipe(meal: Meal)

    suspend fun provideRecipes(): LiveData<List<Meal>>

    suspend fun getFavMealIfExist(idMeal : Int?) : Boolean
}