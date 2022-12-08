package com.muhammetkdr.myrecipeapp.data.local

import com.muhammetkdr.myrecipeapp.domain.source.local.LocalDataSource
import com.muhammetkdr.myrecipeapp.model.meal.Meal
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class LocalDataSourceImpl @Inject constructor(
    private val recipesDao: RecipesDao,
    private val DispatcherIO: CoroutineContext
) : LocalDataSource {

    override suspend fun insertRecipe(meal: Meal) = withContext(DispatcherIO) {
        recipesDao.insertRecipe(meal)
    }

    override suspend fun deleteRecipe(meal: Meal) = withContext(DispatcherIO) {
        recipesDao.deleteRecipe(meal)
    }

    override suspend fun provideRecipes(): List<Meal> = withContext(DispatcherIO) {
        recipesDao.provideRecipes()
    }

    override suspend fun getFavMealIfExist(idMeal: Int?): Boolean = withContext(DispatcherIO) {
        recipesDao.getFavMealIfExist(idMeal)
    }

    override suspend fun deleteFromFavorites(idMeal: Int) = withContext(DispatcherIO) {
        recipesDao.deleteFromFavorites(idMeal)
    }
}