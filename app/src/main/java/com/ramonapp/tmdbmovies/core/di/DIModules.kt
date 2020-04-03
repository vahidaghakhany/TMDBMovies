package com.ramonapp.tmdbmovies.core.di

import com.ramonapp.tmdbmovies.data.MovieRepository
import com.ramonapp.tmdbmovies.data.remote.ApiClient
import com.ramonapp.tmdbmovies.data.remote.MovieApi
import com.ramonapp.tmdbmovies.detail.DetailViewModel
import com.ramonapp.tmdbmovies.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val module = module {

    single {
        MovieRepository(ApiClient.getRetrofitInstance().create(MovieApi::class.java))
    }

    viewModel { HomeViewModel(get()) }

    viewModel { DetailViewModel(get()) }
}

val diModules = listOf(module)
