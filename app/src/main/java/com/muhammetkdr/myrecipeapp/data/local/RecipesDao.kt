package com.muhammetkdr.myrecipeapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.muhammetkdr.myrecipeapp.model.meal.Meal

@Dao
interface RecipesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRecipe(meal: Meal)

    @Delete
    suspend fun deleteRecipe(meal: Meal)

    @Query("SELECT * FROM recipes")
    fun provideRecipes(): LiveData<List<Meal>>

    @Query("SELECT EXISTS(SELECT * FROM recipes WHERE idMeal = :idMeal)")
    suspend fun isRowExist(idMeal: Int?): Boolean

}