package com.devlabs.domain.repository

import com.devlabs.domain.entity.Movie
import com.devlabs.domain.entity.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun favoriteMovie(movie: Movie)
    suspend fun fetchMoviesFromApi(): Flow<ResultWrapper<Unit>>
    fun getMoviesFromDatabase(): Flow<List<Movie>>
    fun getFavoriteMovies(): Flow<ResultWrapper<List<Movie>>>
}