package com.devlabs.data.di

import com.devlabs.data.repository.MoviesRepositoryImpl
import com.devlabs.domain.repository.MoviesRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory {
        MoviesRepositoryImpl(
            get()
        ) as MoviesRepository
    }
}