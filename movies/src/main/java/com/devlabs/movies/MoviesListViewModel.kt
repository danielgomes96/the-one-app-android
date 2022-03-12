package com.devlabs.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devlabs.domain.entity.Movie
import com.devlabs.domain.entity.ResultWrapper
import com.devlabs.domain.usecase.GetMoviesListUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesListViewModel(
    private val getMoviesListUseCase: GetMoviesListUseCase
) : ViewModel() {

    private val _moviesListLiveData = MutableLiveData<ResultWrapper<List<Movie>>>(ResultWrapper.InitialState())
    val moviesListLiveData: LiveData<ResultWrapper<List<Movie>>>
        get() = _moviesListLiveData

    fun getMoviesList() = CoroutineScope(Dispatchers.IO).launch {
        getMoviesListUseCase.execute().collect {
            _moviesListLiveData.postValue(it)
        }
    }
}