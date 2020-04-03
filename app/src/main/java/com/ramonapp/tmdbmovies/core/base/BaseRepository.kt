package com.ramonapp.tmdbmovies.core.base

import com.ramonapp.tmdbmovies.data.remote.RetrofitResult

open class BaseRepository {

    fun <T> execute(response: RetrofitResult<T>): APIResult<T> {
        return when (response) {
            is RetrofitResult.RetrofitSuccess -> APIResult.Success(response.data)
            is RetrofitResult.RetrofitFailure -> APIResult.Error(response.statusCode, response.e)
            else -> APIResult.Error(0, Exception("UnKnown"))
        }
    }
}



sealed class APIResult<out T> {
    data class Success<T>(val data: T?) : APIResult<T>()
    data class Error(val statusCode: Int?, val e: Exception) : APIResult<Nothing>()
}