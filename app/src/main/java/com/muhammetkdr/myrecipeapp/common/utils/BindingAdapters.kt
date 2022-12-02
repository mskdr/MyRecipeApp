package com.muhammetkdr.myrecipeapp.common.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("setImage")
fun setImage(imageView: ImageView, imageUrl: String?) {
    Glide.with(imageView).load(imageUrl).into(imageView)
}

@BindingAdapter("setIngredients")
fun setIngredients(imageView: ImageView, ingredient: String?) {
    Glide.with(imageView).load("https://www.themealdb.com/images/ingredients/${ingredient}-Small.png").into(imageView)
}

