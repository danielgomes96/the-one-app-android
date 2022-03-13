package com.devlabs.movies

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devlabs.characters.CharactersListAdapter
import com.devlabs.domain.entity.Character
import com.devlabs.domain.entity.Movie

class MoviesListAdapter(
    private val context: Context,
    private val onMovieClicked: () -> (Unit)
):  ListAdapter<Movie, MoviesListAdapter.Holder>(MoviesListAdapter) {
    private var moviesList = emptyList<Movie>()

    fun setList(list: List<Movie>) {
        moviesList = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate((R.layout.item_movie), parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(context, moviesList[position], onMovieClicked)
    }

    override fun getItemCount(): Int = moviesList.size

    private companion object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    inner class Holder(view: View): RecyclerView.ViewHolder(view) {
        private val tvTitle: TextView = view.findViewById(R.id.text_movie_title)
        private val tvScore: TextView = view.findViewById(R.id.text_movie_score)
        private val tvDuration: TextView = view.findViewById(R.id.text_movie_duration)

        fun bind(context: Context, movie: Movie, movieClicked: () -> (Unit)) {
            tvTitle.text = movie.name
            val durationInHours = (movie.duration / 60)
            val durationInMinutes = movie.duration % 60

            tvDuration.text = String.format(context.getString(R.string.movie_duration), durationInHours, durationInMinutes)
            tvScore.text = String.format("%.1f", movie.score / 10)

            itemView.setOnClickListener {
                movieClicked()
            }
        }
    }
}