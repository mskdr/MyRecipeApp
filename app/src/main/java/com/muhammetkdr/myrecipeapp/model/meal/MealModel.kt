package com.muhammetkdr.myrecipeapp.model.meal


import com.google.gson.annotations.SerializedName

data class MealModel(
    @SerializedName("meals")
    val meals: List<Meal?>?
)