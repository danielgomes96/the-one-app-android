package com.devlabs.data.repository

import com.devlabs.data.mapper.MoviesRemoteMapper
import com.devlabs.data.service.TheOneAPI
import com.devlabs.domain.entity.Movie
import com.devlabs.domain.entity.ResultWrapper
import com.devlabs.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MoviesRepositoryImpl(
    private val theOneAPI: TheOneAPI
) : MoviesRepository {
    override suspend fun getMovies(): Flow<ResultWrapper<List<Movie>>> = flow {
        emit(ResultWrapper.Loading)
        runCatching {
            theOneAPI.getMovies()
        }.onSuccess {
            if (it.body()?.moviesList?.isNullOrEmpty() == true) {
                emit(ResultWrapper.Empty)
            } else {
                emit(ResultWrapper.Success(
                    MoviesRemoteMapper().transform(it.body()))
                )
            }
        }.onFailure {
            emit(ResultWrapper.GenericError(null, it.localizedMessage))
        }
    }
}