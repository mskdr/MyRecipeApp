package com.muhammetkdr.myrecipeapp.common.utils

//sealed class Resource<out T : Any> {
//    object Loading : Resource<Nothing>()
//    data class Success<out T : Any>(val data: T) : Resource<T>()
//    data class Error(val throwable: Throwable) : Resource<Nothing>()
//}
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
    val networkError: Boolean = false
) {
    object Loading : Resource<Nothing>()
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(networkError: Boolean, message: String?) :
        Resource<T>(data = null, message = message, networkError = networkError)
}