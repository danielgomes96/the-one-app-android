package com.devlabs.movies.di

import com.devlabs.movies.MoviesListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val moviesModule = module {
    viewModel {
        MoviesListViewModel(get(), get(), get(), get())
    }
}