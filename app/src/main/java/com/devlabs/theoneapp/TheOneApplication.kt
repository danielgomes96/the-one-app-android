package com.devlabs.theoneapp

import android.app.Application
import com.devlabs.characters.di.charactersModule
import com.devlabs.data.di.repositoryModule
import com.devlabs.data.di.serviceModule
import com.devlabs.domain.di.domainModule
import com.devlabs.movies.di.moviesModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class TheOneApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TheOneApplication)
            modules(listOf(domainModule, moviesModule, charactersModule, serviceModule, repositoryModule))
        }
    }
}