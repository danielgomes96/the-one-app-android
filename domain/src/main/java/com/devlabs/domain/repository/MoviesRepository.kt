package com.devlabs.domain.repository

import com.devlabs.domain.entity.Movie
import com.devlabs.domain.entity.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun favoriteMovie(movie: Movie): Flow<ResultWrapper<Unit>>
    suspend fun requestMoviesFromApi()
    fun getMoviesFromDatabase(): Flow<List<Movie>>
    fun getFavoriteMovies(): Flow<ResultWrapper<List<Movie>>>
}