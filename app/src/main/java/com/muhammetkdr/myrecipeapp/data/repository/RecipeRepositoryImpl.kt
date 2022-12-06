package com.muhammetkdr.myrecipeapp.data.repository

import androidx.lifecycle.LiveData
import com.muhammetkdr.myrecipeapp.domain.repository.RecipeRepository
import com.muhammetkdr.myrecipeapp.domain.source.local.LocalDataSource
import com.muhammetkdr.myrecipeapp.domain.source.remote.RemoteDataSource
import com.muhammetkdr.myrecipeapp.model.category.CategoryModel
import com.muhammetkdr.myrecipeapp.model.meal.Meal
import com.muhammetkdr.myrecipeapp.model.meal.MealModel
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : RecipeRepository {

    override suspend fun searchMealWithName(searchWithName: String): MealModel  {
        return remoteDataSource.searchMealWithName(searchWithName)
    }

    override suspend fun searchMealWithFirstLetter(searchWithFirstLetter: String): MealModel {
       return remoteDataSource.searchMealWithFirstLetter(searchWithFirstLetter)
    }

    override suspend fun findMealById(searchMealId: Int): MealModel {
       return remoteDataSource.findMealById(searchMealId)
    }

    override suspend fun randomMeal(): MealModel {
       return remoteDataSource.randomMeal()
    }

    override suspend fun getCategories(): CategoryModel {
        return remoteDataSource.getCategories()
    }

    override suspend fun listByType(): MealModel {
       return remoteDataSource.listByType()
    }

    override suspend fun listByCountry(): MealModel {
       return remoteDataSource.listByCountry()
    }

    override suspend fun allMealList(): MealModel {
       return remoteDataSource.allMealList()
    }

    override suspend fun filterByItemName(itemName: String): MealModel {
       return remoteDataSource.filterByItemName(itemName)
    }

    override suspend fun filterByCategory(categoryName: String): MealModel {
       return remoteDataSource.filterByCategory(categoryName)
    }

    override suspend fun filterByNation(nationName: String): MealModel {
       return remoteDataSource.filterByNation(nationName)
    }

    override suspend fun insertRecipe(meal: Meal) = localDataSource.insertRecipe(meal)

    override suspend fun deleteRecipe(meal: Meal) = localDataSource.deleteRecipe(meal)

    override suspend fun provideRecipes(): List<Meal>? =  localDataSource.provideRecipes()

    override suspend fun getFavMealIfExist(idMeal: Int?) = localDataSource.getFavMealIfExist(idMeal)

}