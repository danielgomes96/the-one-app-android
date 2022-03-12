package com.devlabs.domain.di

import com.devlabs.domain.usecase.GetMoviesListUseCase
import com.devlabs.domain.usecase.GetMoviesListUseCaseImpl
import org.koin.dsl.module

val domainModule = module {
    factory {
        GetMoviesListUseCaseImpl(
            get()
        ) as GetMoviesListUseCase
    }
}