package com.ramonapp.tmdbmovies.data

import com.ramonapp.tmdbmovies.core.base.BaseRepository
import com.ramonapp.tmdbmovies.data.remote.MovieApi

class MovieRepository(private val api: MovieApi) : BaseRepository() {

    suspend fun getLatestMovies(page: Int, from: String?, to: String?) =
        execute(api.getMovies(page, from, to))

    suspend fun getMovieDetail(id: Long) = execute(api.getMovieDetail(id))

}