package com.muhammetkdr.myrecipeapp.data.repository

import androidx.lifecycle.LiveData
import com.muhammetkdr.myrecipeapp.base.BaseRepository
import com.muhammetkdr.myrecipeapp.common.utils.Resource
import com.muhammetkdr.myrecipeapp.domain.repository.RecipeRepository
import com.muhammetkdr.myrecipeapp.domain.source.local.LocalDataSource
import com.muhammetkdr.myrecipeapp.domain.source.remote.RemoteDataSource
import com.muhammetkdr.myrecipeapp.model.meal.Meal
import com.muhammetkdr.myrecipeapp.model.meal.MealModel
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : BaseRepository() , RecipeRepository {

    override suspend fun searchMealWithName(searchWithName: String): Resource<MealModel> = safeApiRequest {
       remoteDataSource.searchMealWithName(searchWithName)
    }

    override suspend fun searchMealWithFirstLetter(searchWithFirstLetter: String): Resource<MealModel> = safeApiRequest {
       remoteDataSource.searchMealWithFirstLetter(searchWithFirstLetter)
    }

    override suspend fun lookUpWithId(searchMealId: Int): Resource<MealModel> = safeApiRequest {
       remoteDataSource.lookUpWithId(searchMealId)
    }

    override suspend fun randomMeal(): Resource<MealModel> = safeApiRequest {
       remoteDataSource.randomMeal()
    }

    override suspend fun listByType(): Resource<MealModel> = safeApiRequest {
       remoteDataSource.listByType()
    }

    override suspend fun listByCountry(): Resource<MealModel> = safeApiRequest {
       remoteDataSource.listByCountry()
    }

    override suspend fun allMealList(): Resource<MealModel> = safeApiRequest {
       remoteDataSource.allMealList()
    }

    override suspend fun filterByItemName(itemName: String): Resource<MealModel> = safeApiRequest {
       remoteDataSource.filterByItemName(itemName)
    }

    override suspend fun filterByCategory(categoryName: String): Resource<MealModel> = safeApiRequest {
       remoteDataSource.filterByCategory(categoryName)
    }

    override suspend fun filterByNation(nationName: String): Resource<MealModel> = safeApiRequest {
       remoteDataSource.filterByNation(nationName)
    }

    override suspend fun insertRecipe(meal: Meal) {
        localDataSource.insertRecipe(meal)
    }

    override suspend fun deleteRecipe(meal: Meal) {
        localDataSource.deleteRecipe(meal)
    }

    override suspend fun provideRecipes(): LiveData<List<Meal>> {
        return localDataSource.provideRecipes()
    }

    override suspend fun isRowExist(idMeal: Int?): Boolean {
        return localDataSource.isRowExist(idMeal)
    }

}