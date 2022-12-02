package com.muhammetkdr.myrecipeapp.ui.viewpager

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.muhammetkdr.myrecipeapp.di.sharedpref.SharedPrefManager
import com.muhammetkdr.myrecipeapp.model.meal.Meal
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewPagerViewModel @Inject constructor(savedStateHandle: SavedStateHandle,private val sharedPref: SharedPreferences): ViewModel()  {



}