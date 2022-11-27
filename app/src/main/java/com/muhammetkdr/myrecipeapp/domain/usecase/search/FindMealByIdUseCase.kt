package com.muhammetkdr.myrecipeapp.domain.usecase.search

import com.muhammetkdr.myrecipeapp.common.utils.Resource
import com.muhammetkdr.myrecipeapp.domain.repository.RecipeRepository
import com.muhammetkdr.myrecipeapp.model.meal.MealModel
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FindMealByIdUseCase @Inject constructor(
    private val recipeRepository: RecipeRepository
) {
    suspend operator fun invoke(mealId : Int): Resource<MealModel> {
        return try {
            Resource.Loading
            Resource.Success(recipeRepository.findMealById(mealId))
        } catch (e: HttpException) {
            Resource.Error(e)
        } catch (e: IOException) {
            Resource.Error(e)
        }
    }
}