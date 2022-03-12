package com.devlabs.movies

import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devlabs.core.extension.gone
import com.devlabs.core.extension.visible
import com.devlabs.domain.entity.Movie
import com.devlabs.domain.entity.ResultWrapper
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesListActivity : AppCompatActivity() {
    private val viewModel by viewModel<MoviesListViewModel>()
    private val moviesAdapter: MoviesListAdapter by lazy {
        MoviesListAdapter(this)
    }

    private val containerRoot: ConstraintLayout by lazy {
        findViewById(R.id.activity_movies_root)
    }

    private val progressLoading: ProgressBar by lazy {
        findViewById(R.id.activity_movies_progress)
    }

    private val rvMovies: RecyclerView by lazy {
        findViewById(R.id.activity_movies_recycler_view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_list)
        viewModel.getMoviesList()
        initObservers()
    }

    private fun initObservers() {
        viewModel.moviesListLiveData.observe(this, ::handleResult)
    }

    private fun handleResult(resultState: ResultWrapper<List<Movie>>?) {
        when (resultState) {
            ResultWrapper.Empty -> {
                displayRetrySnackBar()
            }
            ResultWrapper.Loading -> {
                progressLoading.visible()
            }
            is ResultWrapper.Success -> {
                progressLoading.gone()
                rvMovies.visible()
                setupMoviesList(resultState.value)
            }
            is ResultWrapper.GenericError -> {
                displayRetrySnackBar()
            }
        }
    }

    private fun displayRetrySnackBar() {
        progressLoading.gone()
        Snackbar.make(containerRoot, getString(R.string.error_message_data), Snackbar.LENGTH_INDEFINITE).setAction(getString(R.string.retry)) {
            viewModel.getMoviesList()
        }.show()
    }

    private fun setupMoviesList(list: List<Movie>) {
        rvMovies.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        moviesAdapter.setList(list)
        rvMovies.adapter = moviesAdapter
    }
}