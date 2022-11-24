package com.muhammetkdr.myrecipeapp.model.error


import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("meals")
    val meals: Any?
)