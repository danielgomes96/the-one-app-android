package com.devlabs.characters.di

import com.devlabs.characters.CharacterDetailsViewModel
import com.devlabs.characters.CharactersListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val charactersModule = module {
    viewModel {
        CharactersListViewModel(get())
    }
    viewModel {
        CharacterDetailsViewModel(get())
    }
}