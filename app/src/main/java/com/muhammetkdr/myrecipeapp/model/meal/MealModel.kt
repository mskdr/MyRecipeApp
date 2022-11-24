package com.muhammetkdr.myrecipeapp.model.meal


import com.google.gson.annotations.SerializedName
import com.muhammetkdr.myrecipeapp.model.meal.Meal

data class MealModel(
    @SerializedName("meals")
    val meals: List<Meal?>?
)