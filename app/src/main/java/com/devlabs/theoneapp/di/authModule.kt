package com.devlabs.theoneapp.di

import com.devlabs.theoneapp.AuthenticationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    viewModel {
        AuthenticationViewModel(get(), get(), get())
    }
}