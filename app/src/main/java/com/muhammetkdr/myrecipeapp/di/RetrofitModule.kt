package com.muhammetkdr.myrecipeapp.di

import com.muhammetkdr.myrecipeapp.common.utils.Const.Companion.BASE_URL
import com.muhammetkdr.myrecipeapp.domain.datasource.remote.RecipeAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideRecipeService(): RecipeAPIService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(RecipeAPIService::class.java)
    }

}