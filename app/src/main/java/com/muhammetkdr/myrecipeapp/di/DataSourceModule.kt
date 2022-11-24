package com.muhammetkdr.myrecipeapp.di

import com.muhammetkdr.myrecipeapp.data.local.LocalDataSourceImpl
import com.muhammetkdr.myrecipeapp.data.local.RecipesDao
import com.muhammetkdr.myrecipeapp.data.remote.RecipeAPIService
import com.muhammetkdr.myrecipeapp.data.remote.RemoteDataSourceImpl
import com.muhammetkdr.myrecipeapp.domain.source.local.LocalDataSource
import com.muhammetkdr.myrecipeapp.domain.source.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        recipeAPIService: RecipeAPIService,
    ): RemoteDataSource =
        RemoteDataSourceImpl(recipeAPIService)

    @Provides
    @Singleton
    fun provideLocalDataSource(
        provideRecipesDao: RecipesDao,
        ioDispatcher: CoroutineContext
    ): LocalDataSource = LocalDataSourceImpl(provideRecipesDao, ioDispatcher)

}