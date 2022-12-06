package com.muhammetkdr.myrecipeapp.domain.usecase

import com.muhammetkdr.myrecipeapp.domain.repository.RecipeRepository
import com.muhammetkdr.myrecipeapp.model.meal.Meal
import javax.inject.Inject

class DeleteRecipeUseCase @Inject constructor(
    private val recipeRepository: RecipeRepository
) {
    suspend operator fun invoke(meal: Meal) = recipeRepository.deleteRecipe(meal)
}