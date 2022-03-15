package com.devlabs.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devlabs.domain.entity.Movie
import com.devlabs.domain.entity.ResultWrapper
import com.devlabs.domain.usecase.FavoriteMovieUseCase
import com.devlabs.domain.usecase.GetFavoriteMoviesUseCase
import com.devlabs.domain.usecase.GetMoviesListUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MoviesListViewModel(
    private val getMoviesListUseCase: GetMoviesListUseCase,
    private val favoriteMovieUseCase: FavoriteMovieUseCase,
    private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase
) : ViewModel() {

    private val _moviesListLiveData = MutableLiveData<List<Movie>>()
    val moviesListLiveData: LiveData<List<Movie>>
        get() = _moviesListLiveData

    private val _favoriteLiveData = MutableLiveData<ResultWrapper<Unit>>(ResultWrapper.InitialState())
    val favoriteLiveData: LiveData<ResultWrapper<Unit>>
        get() = _favoriteLiveData

    private val _favoriteMoviesLiveData = MutableLiveData<ResultWrapper<List<Movie>>>()
    val favoriteMoviesLiveData: LiveData<ResultWrapper<List<Movie>>>
        get() = _favoriteMoviesLiveData


    fun getMoviesList() = CoroutineScope(Dispatchers.IO).launch {
        getMoviesListUseCase.execute().collect {
            _moviesListLiveData.postValue(it)
        }
    }

    fun favoriteMovie(movie: Movie) = CoroutineScope(Dispatchers.IO).launch {
        favoriteMovieUseCase.execute(movie).collect {
            _favoriteLiveData.postValue(it)
        }
    }

    fun getFavoriteMovies() = CoroutineScope(Dispatchers.IO).launch {
        getFavoriteMoviesUseCase.execute().collect {
            _favoriteMoviesLiveData.postValue(it)
        }
    }
}