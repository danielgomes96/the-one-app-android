package com.devlabs.domain.repository

import com.devlabs.domain.entity.Movie
import com.devlabs.domain.entity.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun getMovies(): Flow<ResultWrapper<List<Movie>>>
}