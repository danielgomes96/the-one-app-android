package com.devlabs.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract val moviesDao: MoviesDao
}