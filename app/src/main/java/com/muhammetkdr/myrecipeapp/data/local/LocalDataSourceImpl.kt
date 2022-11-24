package com.muhammetkdr.myrecipeapp.data.local

import androidx.lifecycle.LiveData
import com.muhammetkdr.myrecipeapp.domain.source.local.LocalDataSource
import com.muhammetkdr.myrecipeapp.model.meal.Meal
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class LocalDataSourceImpl @Inject constructor(
    private val recipesDao: RecipesDao,
    private val ioDispatcher: CoroutineContext
) : LocalDataSource {

    override suspend fun insertRecipe(meal: Meal) = withContext(ioDispatcher) {
        recipesDao.insertRecipe(meal)
    }

    override suspend fun deleteRecipe(meal: Meal) = withContext(ioDispatcher) {
        recipesDao.deleteRecipe(meal)
    }

    override suspend fun provideRecipes(): LiveData<List<Meal>> = withContext(ioDispatcher) {
        recipesDao.provideRecipes()
    }

    override suspend fun isRowExist(idMeal: Int?): Boolean = withContext(ioDispatcher) {
        recipesDao.isRowExist(idMeal)
    }
}