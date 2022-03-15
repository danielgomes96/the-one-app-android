package com.devlabs.domain.usecase

import com.devlabs.domain.entity.Movie
import com.devlabs.domain.entity.ResultWrapper
import com.devlabs.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

interface GetFavoriteMoviesUseCase {
    fun execute(): Flow<ResultWrapper<List<Movie>>>
}

class GetFavoriteMoviesUseCaseImpl(
    private val moviesRepository: MoviesRepository
) : GetFavoriteMoviesUseCase {
    override fun execute(): Flow<ResultWrapper<List<Movie>>> = moviesRepository.getFavoriteMovies()
}