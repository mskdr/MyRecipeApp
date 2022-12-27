package com.muhammetkdr.myrecipeapp.common.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide
import com.muhammetkdr.myrecipeapp.R

@BindingAdapter("setImage")
fun ImageView.setImage(imageUrl: String?) {
    Glide.with(this).load(imageUrl).into(this)
}

@BindingAdapter("setIngredients")
fun ImageView.setIngredients(ingredient: String?) {
    Glide.with(this).load("https://www.themealdb.com/images/ingredients/${ingredient}-Small.png").into(this)
}

@BindingAdapter("favoriteState")
fun ImageView.favoriteState(isFavorite: LiveData<Boolean>) {
    if (isFavorite.value == true) this.setImageResource(R.drawable.ic_favorite_selected)
    else this.setImageResource(R.drawable.ic_favorite_unselected)
}