package com.muhammetkdr.myrecipeapp.data.remote

import com.muhammetkdr.myrecipeapp.domain.source.remote.RemoteDataSource
import com.muhammetkdr.myrecipeapp.model.category.CategoryModel
import com.muhammetkdr.myrecipeapp.model.meal.MealModel
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val recipeAPIService: RecipeAPIService): RemoteDataSource {

    override suspend fun searchMealWithName(searchWithName: String): MealModel {
       return recipeAPIService.searchMealWithName(searchWithName = searchWithName)
    }

    override suspend fun searchMealWithFirstLetter(searchWithFirstLetter: String): MealModel {
        return recipeAPIService.searchMealWithFirstLetter(searchWithFirstLetter = searchWithFirstLetter)
    }

    override suspend fun findMealById(searchMealId: Int): MealModel {
        return recipeAPIService.findMealById(searchMealId = searchMealId)
    }

    override suspend fun randomMeal(): MealModel {
        return recipeAPIService.randomMeal()
    }

    override suspend fun getCategories(): CategoryModel {
        return recipeAPIService.getCategories()
    }

    override suspend fun listByType(): MealModel {
        return recipeAPIService.listByType()
    }

    override suspend fun listByCountry(): MealModel {
        return recipeAPIService.listByCountry()
    }

    override suspend fun allMealList(): MealModel {
        return recipeAPIService.allMealList()
    }

    override suspend fun filterByItemName(itemName: String): MealModel {
        return recipeAPIService.filterByItemName(itemName = itemName)
    }

    override suspend fun filterByCategory(categoryName: String): MealModel {
        return recipeAPIService.filterByCategory(categoryName = categoryName)
    }

    override suspend fun filterByNation(nationName: String): MealModel {
        return recipeAPIService.filterByNation(nationName = nationName)
    }
}