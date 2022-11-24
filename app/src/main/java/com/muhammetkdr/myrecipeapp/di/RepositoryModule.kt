package com.muhammetkdr.myrecipeapp.di

import com.muhammetkdr.myrecipeapp.data.repository.RecipeRepositoryImpl
import com.muhammetkdr.myrecipeapp.domain.repository.RecipeRepository
import com.muhammetkdr.myrecipeapp.domain.source.local.LocalDataSource
import com.muhammetkdr.myrecipeapp.domain.source.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideProductsRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ): RecipeRepository = RecipeRepositoryImpl(remoteDataSource, localDataSource)
}