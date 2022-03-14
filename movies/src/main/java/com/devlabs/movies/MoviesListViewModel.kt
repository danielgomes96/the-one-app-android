package com.devlabs.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devlabs.domain.entity.Movie
import com.devlabs.domain.entity.ResultWrapper
import com.devlabs.domain.usecase.FavoriteMovieUseCase
import com.devlabs.domain.usecase.GetMoviesListUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesListViewModel(
    private val getMoviesListUseCase: GetMoviesListUseCase,
    private val favoriteMovieUseCase: FavoriteMovieUseCase
) : ViewModel() {

    private val _moviesListLiveData = MutableLiveData<ResultWrapper<List<Movie>>>(ResultWrapper.InitialState())
    val moviesListLiveData: LiveData<ResultWrapper<List<Movie>>>
        get() = _moviesListLiveData

    private val _favoriteLiveData = MutableLiveData<ResultWrapper<Unit>>(ResultWrapper.InitialState())
    val favoriteLiveData: LiveData<ResultWrapper<Unit>>
        get() = _favoriteLiveData

    fun getMoviesList() = CoroutineScope(Dispatchers.IO).launch {
        getMoviesListUseCase.execute().collect {
            _moviesListLiveData.postValue(it)
        }
    }

    fun favoriteMovie(movieId: String) = CoroutineScope(Dispatchers.IO).launch {
        favoriteMovieUseCase.execute(movieId).collect {
            _favoriteLiveData.postValue(it)
        }
    }
}