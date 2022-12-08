package com.muhammetkdr.myrecipeapp.domain.usecase

import com.muhammetkdr.myrecipeapp.domain.repository.RecipeRepository
import javax.inject.Inject

class DeleteFromFavoritesUserCase @Inject constructor(
    private val recipeRepository: RecipeRepository
) {
    suspend operator fun invoke(id: Int) = recipeRepository.deleteFromFavorites(id)
}