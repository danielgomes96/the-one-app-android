package com.devlabs.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {
    @Query("select * from movies")
    fun observeMovies(): Flow<List<Movie>>

    @Query("select * from movies")
    fun getMovies(): List<Movie>

    @Query("select * from movies WHERE isFavorite=:isFavorite")
    fun getFavoriteMovies(isFavorite: Boolean): List<Movie>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addMovies(movies: List<Movie>)

    @Update
    fun updateMovie(movie: Movie)
}