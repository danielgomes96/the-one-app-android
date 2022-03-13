package com.devlabs.domain.di

import com.devlabs.domain.usecase.*
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
    factory {
        GetCharacterQuotesUseCaseImpl(
            get()
        ) as GetCharacterQuotesUseCase
    }
}