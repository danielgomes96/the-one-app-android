package com.devlabs.domain.usecase

import com.devlabs.domain.entity.Movie
import com.devlabs.domain.entity.ResultWrapper
import com.devlabs.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

class GetMoviesListUseCaseImpl(
    private val moviesRepository: MoviesRepository
) : GetMoviesListUseCase {
    override suspend fun execute(): Flow<ResultWrapper<List<Movie>>> =
        moviesRepository.fetchMoviesFromApi()
}

interface GetMoviesListUseCase {
    suspend fun execute(): Flow<ResultWrapper<List<Movie>>>
}