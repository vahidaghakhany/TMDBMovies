package com.ramonapp.tmdbmovies.data.remote

import com.ramonapp.tmdbmovies.BuildConfig
import com.ramonapp.tmdbmovies.data.MovieModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("discover/movie")
    suspend fun getMovies(
        @Query("page") page: Int,
        @Query("release_date.gte") from: String?,
        @Query("release_date.lte") to: String?,
        @Query("api_key") pageIndex: String = BuildConfig.API_KEY
    ): RetrofitResult<ListResponse<MovieModel>>

    @GET("movie/{id}")
    suspend fun getMovieDetail(
        @Path("id") id: Long,
        @Query("api_key") pageIndex: String = BuildConfig.API_KEY
    ): RetrofitResult<MovieModel>

}