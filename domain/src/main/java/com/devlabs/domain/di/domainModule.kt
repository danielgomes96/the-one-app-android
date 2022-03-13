package com.devlabs.domain.di

import com.devlabs.domain.usecase.GetCharactersUseCase
import com.devlabs.domain.usecase.GetCharactersUseCaseImpl
import com.devlabs.domain.usecase.GetMoviesListUseCase
import com.devlabs.domain.usecase.GetMoviesListUseCaseImpl
import org.koin.dsl.module

val domainModule = module {
    factory {
        GetMoviesListUseCaseImpl(
            get()
        ) as GetMoviesListUseCase
    }
    factory {
        GetCharactersUseCaseImpl(
            get()
        ) as GetCharactersUseCase
    }
}