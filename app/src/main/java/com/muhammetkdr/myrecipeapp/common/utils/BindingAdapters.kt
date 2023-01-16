package com.muhammetkdr.myrecipeapp.common.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide
import com.muhammetkdr.myrecipeapp.BuildConfig
import com.muhammetkdr.myrecipeapp.R

@BindingAdapter("setImage")
fun ImageView.setImage(imageUrl: String?) {
    Glide.with(this).load(imageUrl)
        .placeholder(R.drawable.ic_downloading)
        .error(R.drawable.ic_error)
        .into(this)
}

@BindingAdapter("setIngredients")
fun ImageView.setIngredients(ingredient: String?) {
    Glide.with(this).load("${BuildConfig.IMAGE_URL}${ingredient}-Small.png")
        .placeholder(R.drawable.ic_downloading)
        .error(R.drawable.ic_error)
        .into(this)
}

@BindingAdapter("favoriteState")
fun ImageView.favoriteState(isFavorite:LiveData<Boolean?>){
    if (isFavorite.value == true && isFavorite.value != null) this.setImageResource(R.drawable.ic_favorite_selected)
    else this.setImageResource(R.drawable.ic_favorite_unselected)
}