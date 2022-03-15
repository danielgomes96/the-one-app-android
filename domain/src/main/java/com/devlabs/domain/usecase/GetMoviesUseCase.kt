package com.devlabs.domain.usecase

import com.devlabs.domain.entity.Movie
import com.devlabs.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

class GetMoviesUseCaseImpl(
    private val moviesRepository: MoviesRepository
) : GetMoviesUseCase {
    override suspend fun execute(): Flow<List<Movie>> {
        return moviesRepository.getMoviesFromDatabase()
    }
}

interface GetMoviesUseCase {
    suspend fun execute(): Flow<List<Movie>>
}