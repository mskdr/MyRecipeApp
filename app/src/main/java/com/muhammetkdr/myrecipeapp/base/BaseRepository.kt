package com.muhammetkdr.myrecipeapp.base

import com.google.gson.Gson
import com.muhammetkdr.myrecipeapp.R
import com.muhammetkdr.myrecipeapp.RecipeApplication.Companion.getAppContext
import com.muhammetkdr.myrecipeapp.common.utils.Resource
import com.muhammetkdr.myrecipeapp.model.error.ErrorResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.lang.Exception

abstract class BaseRepository() {
    suspend fun <T> safeApiRequest(
        apiRequest: suspend () -> T
    ): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiRequest.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        Resource.Error(
                            false,
                            errorBodyParser(throwable.response()?.errorBody()?.string())
                        )
                    }
                    else -> Resource.Error(true, throwable.localizedMessage)
                }
            }
        }
    }
}

private fun errorBodyParser(error: String?): String {
    error?.let {
        return try {
            val errorResponse = Gson().fromJson(error, ErrorResponse::class.java)
            val errorMessage = errorResponse.meals // error durumunda api'dan meals null geliyor
            if(errorMessage == null) return getAppContext().resources.getString(R.string.meals_null)
            else getAppContext().resources.getString(R.string.error_message)
        } catch (e: Exception) {
            getAppContext().resources.getString(R.string.error_message)
        }
    }
    return getAppContext().resources.getString(R.string.error_message)
}