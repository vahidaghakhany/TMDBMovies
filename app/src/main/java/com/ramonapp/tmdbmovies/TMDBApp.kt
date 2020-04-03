package com.ramonapp.tmdbmovies

import android.app.Application
import com.ramonapp.tmdbmovies.core.di.diModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TMDBApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@TMDBApp)
            modules(diModules)
        }
    }
}