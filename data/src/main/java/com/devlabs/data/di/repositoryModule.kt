package com.devlabs.data.di

import com.devlabs.data.repository.AuthRepositoryImpl
import com.devlabs.data.repository.CharactersRepositoryImpl
import com.devlabs.data.repository.MoviesRepositoryImpl
import com.devlabs.domain.repository.AuthRepository
import com.devlabs.domain.repository.CharactersRepository
import com.devlabs.domain.repository.MoviesRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory {
        MoviesRepositoryImpl(
            get(),
            get()
        ) as MoviesRepository
    }

    factory {
        CharactersRepositoryImpl(
            get()
        ) as CharactersRepository
    }

    factory {
        AuthRepositoryImpl(
            get()
        ) as AuthRepository
    }
}