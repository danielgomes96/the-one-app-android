package com.devlabs.movies

import android.content.Intent
import android.os.Bundle
import android.system.Os.bind
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devlabs.characters.CharactersListActivity
import com.devlabs.core.extension.bind
import com.devlabs.core.extension.gone
import com.devlabs.core.extension.visible
import com.devlabs.domain.entity.Movie
import com.devlabs.domain.entity.ResultWrapper
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesListActivity : AppCompatActivity() {

    private val viewModel by viewModel<MoviesListViewModel>()
    private val moviesAdapter: MoviesListAdapter by lazy {
        MoviesListAdapter(this, {
            openCharactersList()
        }, ::favoriteMovie)
    }

    private val containerRoot: LinearLayout by bind(R.id.activity_movies_root)
    private val progressLoading: ProgressBar by bind(R.id.activity_movies_progress)
    private val rvMovies: RecyclerView by bind(R.id.activity_movies_recycler_view)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_list)
        setSupportActionBar(findViewById(R.id.toolbar))
        viewModel.fetchMoviesFromApi()
        initObservers()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.movies_favorites_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun initObservers() {
        viewModel.apiStatusLiveData.observe(this, ::handleResult)
        viewModel.moviesListLiveData.observe(this, ::setupMoviesFromDatabase)
    }

    private fun setupMoviesFromDatabase(list: List<Movie>) {
        setupMoviesList(list)
    }

    private fun favoriteMovie(movie: Movie) {
        viewModel.favoriteMovie(movie)
    }

    private fun openCharactersList() {
        val intent = Intent(this, CharactersListActivity::class.java)
        startActivity(intent)
    }

    private fun handleResult(resultState: ResultWrapper<Unit>) {
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
                viewModel.getMoviesList()
            }
            is ResultWrapper.GenericError -> {
                displayRetrySnackBar()
            }
        }
    }

    private fun displayRetrySnackBar() {
        progressLoading.gone()
        Snackbar.make(containerRoot, getString(R.string.error_message_data), Snackbar.LENGTH_INDEFINITE).setAction(getString(R.string.retry)) {
            viewModel.fetchMoviesFromApi()
        }.show()
    }

    private fun setupMoviesList(list: List<Movie>) {
        rvMovies.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        moviesAdapter.setList(list)
        rvMovies.adapter = moviesAdapter
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_favorite -> {
            val intent = Intent(this, FavoriteMoviesActivity::class.java)
            startActivity(intent)
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}