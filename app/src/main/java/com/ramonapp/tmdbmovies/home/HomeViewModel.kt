package com.ramonapp.tmdbmovies.home

import androidx.lifecycle.viewModelScope
import com.ramonapp.tmdbmovies.core.base.APIResult
import com.ramonapp.tmdbmovies.core.base.BaseViewModel
import com.ramonapp.tmdbmovies.core.mvvm.SingleLiveEvent
import com.ramonapp.tmdbmovies.data.MovieModel
import com.ramonapp.tmdbmovies.data.MovieRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val movieRepository: MovieRepository): BaseViewModel() {

    val movieList = SingleLiveEvent<List<MovieModel>>()

    fun getMovies(page: Int = 1, from: String? = null, to: String? = null) = viewModelScope.launch {
        val result = movieRepository.getLatestMovies(page, from, to)
        if (result is APIResult.Success) {
            movieList.value = result.data?.results
        } else if (result is APIResult.Error) {
            mError.value = result.e.toString()
        }
    }

}