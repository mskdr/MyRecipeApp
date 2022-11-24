package com.muhammetkdr.myrecipeapp.data.remote

import com.muhammetkdr.myrecipeapp.model.meal.MealModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeAPIService {

    @GET("api/json/v1/1/search.php")
    suspend fun searchMealWithName(
        @Query("s") searchWithName: String
    ): MealModel

    @GET("api/json/v1/1/search.php")
    suspend fun searchMealWithFirstLetter(
        @Query("f") searchWithFirstLetter: String
    ): MealModel

    @GET("api/json/v1/1/lookup.php")
    suspend fun lookUpWithId(
        @Query("i") searchMealId: Int
    ): MealModel

    @GET("/api/json/v1/1/random.php")
    suspend fun randomMeal(
    ): MealModel

//        @GET("api/json/v1/1/categories.php")
//        suspend fun categories() : Categories

    @GET("api/json/v1/1/list.php?c=list")
    suspend fun listByType(): MealModel   //strCategory verecek sadece

    @GET("api/json/v1/1/list.php?a=list")
    suspend fun listByCountry(): MealModel //strArea verecek sadece

    @GET("api/json/v1/1/list.php?i=list")
    suspend fun allMealList(): MealModel //idIngredient, strIngredient , strDescription , strType- nullable-all list

    @GET("api/json/v1/1/filter.php")
    suspend fun filterByItemName(
        @Query("i")
        itemName: String  //strMeal strMealThumb idMeal
    ): MealModel

    @GET("api/json/v1/1/filter.php")
    suspend fun filterByCategory(
        @Query("c")
        categoryName: String //strMeal strMealThumb idMeal
    ): MealModel

    @GET("api/json/v1/1/filter.php")
    suspend fun filterByNation(
        @Query("a")
        nationName: String  //strMeal strMealThumb idMeal
    ): MealModel

}