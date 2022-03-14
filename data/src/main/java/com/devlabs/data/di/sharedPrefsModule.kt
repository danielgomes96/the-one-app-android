package com.devlabs.data.di

import android.app.Application
import android.content.SharedPreferences
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val sharedPrefsModule = module {
    single{
        getSharedPrefs(androidApplication())
    }

    single<SharedPreferences.Editor> {
        getSharedPrefs(androidApplication()).edit()
    }
}

fun getSharedPrefs(androidApplication: Application): SharedPreferences {
    return  androidApplication.getSharedPreferences("the_one_app",  android.content.Context.MODE_PRIVATE)
}