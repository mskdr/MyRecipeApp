package com.muhammetkdr.myrecipeapp.domain.usecase

import com.muhammetkdr.myrecipeapp.common.utils.Resource
import com.muhammetkdr.myrecipeapp.domain.repository.RecipeRepository
import com.muhammetkdr.myrecipeapp.model.meal.Meal
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ProvideRecipeUseCase @Inject constructor(
    private val recipeRepository: RecipeRepository
) {
    suspend operator fun invoke(): Resource<List<Meal>> {
        return try {
            Resource.Loading
            Resource.Success( recipeRepository.provideRecipes())
        } catch (e: HttpException) {
            Resource.Error(e)
        } catch (e: IOException) {
            Resource.Error(e)
        }
    }

}