package com.devlabs.data.repository

import com.devlabs.data.local.MoviesDao
import com.devlabs.data.mapper.MoviesLocalMapper
import com.devlabs.data.mapper.MoviesRemoteMapper
import com.devlabs.data.service.TheOneAPI
import com.devlabs.domain.entity.Movie
import com.devlabs.domain.entity.ResultWrapper
import com.devlabs.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class MoviesRepositoryImpl(
    private val theOneAPI: TheOneAPI,
    private val moviesDao: MoviesDao
) : MoviesRepository {

    override suspend fun fetchMoviesFromApi(): Flow<ResultWrapper<List<Movie>>> = flow {
        emit(ResultWrapper.Loading)
        runCatching {
            theOneAPI.getMovies()
        }.onSuccess {
            if (it.body()?.moviesList?.isNullOrEmpty() == true) {
                emit(ResultWrapper.Empty)
            } else {
                moviesDao.addMovies(
                    MoviesLocalMapper().transform(it.body())
                )
                emit(ResultWrapper.Success(
                    MoviesRemoteMapper().transform(moviesDao.getMovies())
                ))
            }
        }.onFailure {
            emit(ResultWrapper.GenericError(null, it.localizedMessage))
        }
    }


    override suspend fun favoriteMovie(movieId: String): Flow<ResultWrapper<Unit>> = flow {
        emit(ResultWrapper.Loading)
        runCatching {
            moviesDao.updateFavoriteStatus(true, movieId)
        }.onSuccess {
            emit(ResultWrapper.Success(Unit))
        }.onFailure {
            emit(ResultWrapper.GenericError(null, it.localizedMessage))
        }
    }
}