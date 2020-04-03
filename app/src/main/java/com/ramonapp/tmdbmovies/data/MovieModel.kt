package com.ramonapp.tmdbmovies.data

import com.google.gson.annotations.SerializedName

data class MovieModel(
    val id: Long,
    val title: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    val overview: String?,
    @SerializedName("vote_average")
    val voteAverage: Float?,
    @SerializedName("release_date")
    val releaseDate: String?
)