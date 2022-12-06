package com.muhammetkdr.myrecipeapp.domain.usecase

import com.muhammetkdr.myrecipeapp.domain.repository.RecipeRepository
import javax.inject.Inject

class GetFavMealIfExistUseCase @Inject constructor(
    private val recipeRepository: RecipeRepository
) {
    suspend operator fun invoke(mealId : Int) = recipeRepository.getFavMealIfExist(mealId)
}