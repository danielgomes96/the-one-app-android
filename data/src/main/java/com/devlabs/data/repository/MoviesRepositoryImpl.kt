package com.devlabs.data.repository

import android.util.Log
import com.devlabs.data.local.MoviesDao
import com.devlabs.data.mapper.MovieLocalMapper
import com.devlabs.data.mapper.MoviesLocalMapper
import com.devlabs.data.mapper.MoviesRemoteMapper
import com.devlabs.data.service.TheOneAPI
import com.devlabs.domain.entity.Movie
import com.devlabs.domain.entity.ResultWrapper
import com.devlabs.domain.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext

class MoviesRepositoryImpl(
    private val theOneAPI: TheOneAPI,
    private val moviesDao: MoviesDao
) : MoviesRepository {

    override suspend fun requestMoviesFromApi() {
        if (moviesDao.getMovies().isEmpty()) {
            withContext(Dispatchers.IO) {
                runCatching {
                    theOneAPI.getMovies()
                }.onSuccess { response ->
                    moviesDao.addMovies(
                        MoviesLocalMapper().transform(response.body())
                    )
                }.onFailure {

                }
            }
        }
    }

    override fun getMoviesFromDatabase(): Flow<List<Movie>> = moviesDao.observeMovies().map {
        MoviesRemoteMapper().transform(it)
    }

    override fun getFavoriteMovies(): Flow<ResultWrapper<List<Movie>>> = flow {
        emit(ResultWrapper.Loading)
        if (moviesDao.getFavoriteMovies(true).isEmpty()) {
            emit(ResultWrapper.Empty)
        } else {
            emit(ResultWrapper.Success(MoviesRemoteMapper().transform(moviesDao.getFavoriteMovies(true))))
        }
    }

    override suspend fun favoriteMovie(movie: Movie): Flow<ResultWrapper<Unit>> = flow {
        emit(ResultWrapper.Loading)
        runCatching {
            moviesDao.updateMovie(MovieLocalMapper().transform(movie))
        }.onSuccess {
            emit(ResultWrapper.Success(Unit))
        }.onFailure {
            emit(ResultWrapper.GenericError(null, it.localizedMessage))
        }
    }
}
