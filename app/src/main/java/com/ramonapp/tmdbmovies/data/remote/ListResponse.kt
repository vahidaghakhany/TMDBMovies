package com.ramonapp.tmdbmovies.data.remote

import com.google.gson.annotations.SerializedName


class ListResponse<T>(
    val page: Long,
    @SerializedName("total_results")
    val totalResult: Long,
    @SerializedName("total_pages")
    val totalPages: Long,
    val results: List<T>?
)


