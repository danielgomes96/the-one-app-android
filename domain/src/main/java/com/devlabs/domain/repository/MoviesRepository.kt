package com.devlabs.domain.repository

import com.devlabs.domain.entity.Movie
import com.devlabs.domain.entity.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun fetchMoviesFromApi(): Flow<ResultWrapper<List<Movie>>>
    suspend fun favoriteMovie(movieId: String): Flow<ResultWrapper<Unit>>
}