package com.muhammetkdr.myrecipeapp.di

import android.content.Context
import androidx.room.Room
import com.muhammetkdr.myrecipeapp.data.local.RecipeDatabase
import com.muhammetkdr.myrecipeapp.data.local.RecipesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomDBModule {

    @Provides
    @Singleton
    fun provideRecipesRoomDB(@ApplicationContext appContext: Context): RecipeDatabase =
        Room.databaseBuilder(
            appContext,
            RecipeDatabase::class.java,
            "recipes_database.db"
        ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideRecipesDAO(recipeRoomDB: RecipeDatabase): RecipesDao =
        recipeRoomDB.recipeDao()
}