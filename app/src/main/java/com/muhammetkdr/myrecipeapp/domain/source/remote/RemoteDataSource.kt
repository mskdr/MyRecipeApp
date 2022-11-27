package com.muhammetkdr.myrecipeapp.domain.source.remote

import com.muhammetkdr.myrecipeapp.model.category.CategoryModel
import com.muhammetkdr.myrecipeapp.model.meal.MealModel

interface RemoteDataSource {

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

}