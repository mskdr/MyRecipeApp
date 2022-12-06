package com.muhammetkdr.myrecipeapp.domain.usecase.list

import com.muhammetkdr.myrecipeapp.common.utils.Resource
import com.muhammetkdr.myrecipeapp.domain.repository.RecipeRepository
import com.muhammetkdr.myrecipeapp.model.category.CategoryModel
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCategoriesUseCase  @Inject constructor(
    private val recipeRepository: RecipeRepository
) {
    suspend operator fun invoke(): Resource<CategoryModel> {
        return try {
            Resource.Loading
            Resource.Success(recipeRepository.getCategories())
        } catch (e: HttpException) {
            Resource.Error(e)
        } catch (e: IOException) {
            Resource.Error(e)
        }
    }
}