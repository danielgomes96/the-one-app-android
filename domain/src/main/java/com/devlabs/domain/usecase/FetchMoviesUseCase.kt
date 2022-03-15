package com.devlabs.domain.usecase

import com.devlabs.domain.entity.ResultWrapper
import com.devlabs.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

class FetchMoviesUseCaseImpl(
    private val moviesRepository: MoviesRepository
) : FetchMoviesUseCase {
    override suspend fun execute(): Flow<ResultWrapper<Unit>> {
        return moviesRepository.fetchMoviesFromApi()
    }
}

interface FetchMoviesUseCase {
    suspend fun execute(): Flow<ResultWrapper<Unit>>
}