package com.devlabs.domain.di

import com.devlabs.domain.usecase.*
import org.koin.dsl.module

val domainModule = module {
    factory {
        FetchMoviesUseCaseImpl(
            get()
        ) as FetchMoviesUseCase
    }
    factory {
        GetMoviesUseCaseImpl(
            get()
        ) as GetMoviesUseCase
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
    factory {
        FavoriteMovieUseCaseImpl(
            get()
        ) as FavoriteMovieUseCase
    }
    factory {
        SavePinUseCaseImpl(
            get()
        ) as SavePinUseCase
    }
    factory {
        HasPinUseCaseImpl(
            get()
        ) as HasPinUseCase
    }
    factory {
        AuthPinUseCaseImpl(
            get()
        ) as AuthPinUseCase
    }
    factory {
        GetFavoriteMoviesUseCaseImpl(
            get()
        ) as GetFavoriteMoviesUseCase
    }
}