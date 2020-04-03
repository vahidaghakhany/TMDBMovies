package com.ramonapp.tmdbmovies.detail

import androidx.lifecycle.viewModelScope
import com.ramonapp.tmdbmovies.core.base.APIResult
import com.ramonapp.tmdbmovies.core.base.BaseViewModel
import com.ramonapp.tmdbmovies.core.mvvm.SingleLiveEvent
import com.ramonapp.tmdbmovies.data.MovieModel
import com.ramonapp.tmdbmovies.data.MovieRepository
import kotlinx.coroutines.launch

class DetailViewModel(private val movieRepository: MovieRepository): BaseViewModel() {

    val movie = SingleLiveEvent<MovieModel>()

    fun getMovie(id: Long) = viewModelScope.launch {
        val result = movieRepository.getMovieDetail(id)
        if (result is APIResult.Success) {
            movie.value = result.data
        } else if (result is APIResult.Error) {
            mError.value = result.e.toString()
        }
    }

}