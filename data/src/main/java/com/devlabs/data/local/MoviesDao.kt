package com.devlabs.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {
    @Query("select * from movies")
    fun getMovies(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addMovies(movies: List<Movie>)

    @Query("UPDATE movies SET isFavorite=:favorite WHERE id = :movieId")
    fun updateFavoriteStatus(favorite: Boolean, movieId: String)
}