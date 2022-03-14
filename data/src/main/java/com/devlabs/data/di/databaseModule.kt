package com.devlabs.data.di

import android.app.Application
import androidx.room.Room
import com.devlabs.data.local.MovieDatabase
import com.devlabs.data.local.MoviesDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    fun provideDataBase(application: Application): MovieDatabase {
        return Room.databaseBuilder(application, MovieDatabase::class.java, "theoneapp_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideDao(database: MovieDatabase): MoviesDao {
        return database.moviesDao
    }
    single { provideDataBase(androidApplication()) }
    single { provideDao(get()) }
}