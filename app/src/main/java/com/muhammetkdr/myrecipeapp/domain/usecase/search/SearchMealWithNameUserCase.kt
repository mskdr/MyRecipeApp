package com.muhammetkdr.myrecipeapp.domain.usecase.search

import com.muhammetkdr.myrecipeapp.common.utils.Resource
import com.muhammetkdr.myrecipeapp.domain.repository.RecipeRepository
import com.muhammetkdr.myrecipeapp.model.meal.MealModel
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SearchMealWithNameUserCase  @Inject constructor(
    private val recipeRepository: RecipeRepository
) {
    suspend operator fun invoke(mealName: String): Resource<MealModel> {
        return try {
            Resource.Loading
            Resource.Success(recipeRepository.searchMealWithName(mealName))
        } catch (e: HttpException) {
            Resource.Error(e)
        } catch (e: IOException) {
            Resource.Error(e)
        }
    }
}