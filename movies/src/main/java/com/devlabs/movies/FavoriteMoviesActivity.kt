package com.devlabs.movies

import android.os.Bundle
import android.view.MenuItem
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devlabs.core.extension.bind
import com.devlabs.core.extension.gone
import com.devlabs.core.extension.visible
import com.devlabs.domain.entity.Movie
import com.devlabs.domain.entity.ResultWrapper
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteMoviesActivity: AppCompatActivity() {

    private val viewModel by viewModel<MoviesListViewModel>()
    private val rvMovies: RecyclerView by bind(R.id.activity_favorite_movies_recycler_view)
    private val tvEmptyFavorites: TextView by bind(R.id.activity_favorite_movies_txt_empty)
    private val progressBar: ProgressBar by bind(R.id.activity_favorite_movies_progress)
    private val favoriteMoviesAdapter: FavoriteMoviesAdapter by lazy {
        FavoriteMoviesAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_movies)
        viewModel.getFavoriteMovies()
        setupActionBar()
        setupObserver()
    }

    private fun setupActionBar() = with (supportActionBar) {
        supportActionBar?.apply {
            title = getString(R.string.favorites_title)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setupObserver() {
        viewModel.favoriteMoviesLiveData.observe(this, ::handleResult)
    }

    private fun handleResult(result: ResultWrapper<List<Movie>>?) {
        when (result) {
            ResultWrapper.Loading -> { progressBar.visible() }
            ResultWrapper.Empty -> {
                progressBar.gone()
                tvEmptyFavorites.visible()
            }
            is ResultWrapper.Success -> setupFavoriteMoviesList(result.value)
        }
    }

    private fun setupFavoriteMoviesList(list: List<Movie>) {
        progressBar.gone()
        rvMovies.visible()
        rvMovies.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        favoriteMoviesAdapter.setList(list)
        rvMovies.adapter = favoriteMoviesAdapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}