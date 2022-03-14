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
}