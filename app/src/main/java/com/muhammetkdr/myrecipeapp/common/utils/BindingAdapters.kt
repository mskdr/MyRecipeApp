package com.muhammetkdr.myrecipeapp.common.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.muhammetkdr.myrecipeapp.R

@BindingAdapter("setImage")
fun setImage(imageView: ImageView, imageUrl: String?) {
    Glide.with(imageView).load(imageUrl).into(imageView)
}

@BindingAdapter("setIngredients")
fun setIngredients(imageView: ImageView, ingredient: String?) {
    Glide.with(imageView).load("https://www.themealdb.com/images/ingredients/${ingredient}-Small.png").into(imageView)
}

@BindingAdapter("favoriteState")
fun favoriteState(imageView: ImageView, isFavorite: Boolean) {
    if (isFavorite) imageView.setImageResource(R.drawable.ic_favorite_selected)
    else imageView.setImageResource(R.drawable.ic_favorite_unselected)
}

