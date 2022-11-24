package com.muhammetkdr.myrecipeapp

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RecipeApplication: Application(){
    companion object {
        private lateinit var instance : RecipeApplication
        fun getAppContext(): Context = instance.applicationContext
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
    }
}