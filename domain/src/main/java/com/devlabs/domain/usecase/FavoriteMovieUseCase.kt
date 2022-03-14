package com.devlabs.domain.usecase

import com.devlabs.domain.entity.ResultWrapper
import com.devlabs.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

interface FavoriteMovieUseCase {
    suspend fun execute(movieId: String): Flow<ResultWrapper<Unit>>
}

class FavoriteMovieUseCaseImpl(
    private val moviesRepository: MoviesRepository
) : FavoriteMovieUseCase {
    override suspend fun execute(movieId: String) : Flow<ResultWrapper<Unit>> =
        moviesRepository.favoriteMovie(movieId)
}