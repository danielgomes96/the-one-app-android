package com.devlabs.domain.usecase

import com.devlabs.domain.entity.Movie
import com.devlabs.domain.entity.ResultWrapper
import com.devlabs.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

interface FavoriteMovieUseCase {
    suspend fun execute(movie: Movie)
}

class FavoriteMovieUseCaseImpl(
    private val moviesRepository: MoviesRepository
) : FavoriteMovieUseCase {
    override suspend fun execute(movie: Movie) {
        moviesRepository.favoriteMovie(movie)
    }
}