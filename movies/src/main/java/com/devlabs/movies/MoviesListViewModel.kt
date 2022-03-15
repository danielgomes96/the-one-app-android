package com.devlabs.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devlabs.domain.entity.Movie
import com.devlabs.domain.entity.ResultWrapper
import com.devlabs.domain.usecase.FavoriteMovieUseCase
import com.devlabs.domain.usecase.FetchMoviesUseCase
import com.devlabs.domain.usecase.GetFavoriteMoviesUseCase
import com.devlabs.domain.usecase.GetMoviesUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesListViewModel(
    private val fetchMoviesUseCase: FetchMoviesUseCase,
    private val getMoviesUseCase: GetMoviesUseCase,
    private val favoriteMovieUseCase: FavoriteMovieUseCase,
    private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase
) : ViewModel() {

    private val _apiStatusLiveData = MutableLiveData<ResultWrapper<Unit>>()
    val apiStatusLiveData: LiveData<ResultWrapper<Unit>>
        get() = _apiStatusLiveData

    private val _moviesListLiveData = MutableLiveData<List<Movie>>()
    val moviesListLiveData: LiveData<List<Movie>>
        get() = _moviesListLiveData

    private val _favoriteMoviesLiveData = MutableLiveData<ResultWrapper<List<Movie>>>()
    val favoriteMoviesLiveData: LiveData<ResultWrapper<List<Movie>>>
        get() = _favoriteMoviesLiveData

    fun fetchMoviesFromApi() = CoroutineScope(Dispatchers.IO).launch {
        fetchMoviesUseCase.execute().collect {
            _apiStatusLiveData.postValue(it)
        }
    }

    fun getMoviesList() = CoroutineScope(Dispatchers.IO).launch {
        getMoviesUseCase.execute().collect {
            _moviesListLiveData.postValue(it)
        }
    }

    fun favoriteMovie(movie: Movie) = CoroutineScope(Dispatchers.IO).launch {
        favoriteMovieUseCase.execute(movie)
    }

    fun getFavoriteMovies() = CoroutineScope(Dispatchers.IO).launch {
        getFavoriteMoviesUseCase.execute().collect {
            _favoriteMoviesLiveData.postValue(it)
        }
    }
}