package com.muhammetkdr.myrecipeapp.data.remote

import com.muhammetkdr.myrecipeapp.BuildConfig
import com.muhammetkdr.myrecipeapp.model.category.CategoryModel
import com.muhammetkdr.myrecipeapp.model.meal.MealModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeAPIService {

    @GET("api/json/v1/{api_key}/search.php")
    suspend fun searchMealWithName(
        @Path("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("s") searchWithName: String
    ): MealModel

    @GET("api/json/v1/{api_key}/search.php")
    suspend fun searchMealWithFirstLetter(
        @Path("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("f") searchWithFirstLetter: String
    ): MealModel

    @GET("api/json/v1/{api_key}/lookup.php")
    suspend fun findMealById(
        @Path("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("i") searchMealId: Int
    ): MealModel

    @GET("/api/json/v1/{api_key}/random.php")
    suspend fun randomMeal(
        @Path("api_key") apiKey: String = BuildConfig.API_KEY
    ): MealModel

    @GET("api/json/v1/{api_key}/categories.php")
    suspend fun getCategories(
        @Path("api_key") apiKey: String = BuildConfig.API_KEY
    ): CategoryModel

    @GET("api/json/v1/{api_key}/list.php?c=list")
    suspend fun listByType(
        @Path("api_key") apiKey: String = BuildConfig.API_KEY
    ): MealModel

    @GET("api/json/v1/{api_key}/list.php?a=list")
    suspend fun listByCountry(
        @Path("api_key") apiKey: String = BuildConfig.API_KEY
    ): MealModel

    @GET("api/json/v1/{api_key}/list.php?i=list")
    suspend fun allMealList(
        @Path("api_key") apiKey: String = BuildConfig.API_KEY
    ): MealModel

    @GET("api/json/v1/{api_key}/filter.php")
    suspend fun filterByItemName(
        @Path("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("i")
        itemName: String
    ): MealModel

    @GET("api/json/v1/{api_key}/filter.php")
    suspend fun filterByCategory(
        @Path("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("c")
        categoryName: String
    ): MealModel

    @GET("api/json/v1/{api_key}/filter.php")
    suspend fun filterByNation(
        @Path("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("a")
        nationName: String,
    ): MealModel
}