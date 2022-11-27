package com.muhammetkdr.myrecipeapp.domain.usecase.list.filter

import com.muhammetkdr.myrecipeapp.common.utils.Resource
import com.muhammetkdr.myrecipeapp.domain.repository.RecipeRepository
import com.muhammetkdr.myrecipeapp.model.meal.MealModel
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FilterByCategoryUseCase @Inject constructor(
    private val recipeRepository: RecipeRepository
) {
    suspend operator fun invoke(category: String): Resource<MealModel> {
        return try {
            Resource.Loading
            Resource.Success(recipeRepository.filterByCategory(category))
        } catch (e: HttpException) {
            Resource.Error(e)
        } catch (e: IOException) {
            Resource.Error(e)
        }
    }
}